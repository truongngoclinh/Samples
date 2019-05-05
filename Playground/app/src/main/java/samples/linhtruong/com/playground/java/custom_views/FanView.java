package samples.linhtruong.com.playground.java.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import samples.linhtruong.com.playground.R;

/**
 * @author linhtruong
 */
public class FanView extends View {
    private final static int SELECTION_COUNT = 4;
    private float width, height;
    private Paint textPaint;
    private Paint dialPaint;
    private int activeSelection;
    private int selectionCount;
    private float radius;

    private int fanOnColor;
    private int fanOffColor;

    // temp
    private final StringBuffer mTempLabel = new StringBuffer(8);
    private final float[] mTempResult = new float[2];

    public FanView(Context context) {
        super(context);
        init(null);
    }

    public FanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void setSelectionCount(int selectionCount) {
        this.selectionCount = selectionCount;
        invalidate();
    }

    private void init(AttributeSet attrs) {
        fanOnColor = Color.GREEN;
        fanOffColor = Color.GRAY;
        selectionCount = SELECTION_COUNT;

        if (attrs != null) {
            TypedArray type = getContext().obtainStyledAttributes(attrs, R.styleable.FanViewAttrs, 0, 0);
            fanOnColor = type.getColor(R.styleable.FanViewAttrs_fanOnColor, fanOnColor);
            fanOffColor = type.getColor(R.styleable.FanViewAttrs_fanOffColor, fanOffColor);
            selectionCount = type.getColor(R.styleable.FanViewAttrs_selectionCount, selectionCount);
            type.recycle();
        }

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        dialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dialPaint.setColor(fanOffColor);

        activeSelection = 0;

        setOnClickListener(v -> {
            // Rotate selection to the next valid choice.
            activeSelection = (activeSelection + 1) % selectionCount;
            // Set dial background color to green if selection is >= 1.
            if (activeSelection >= 1) {
                dialPaint.setColor(fanOnColor);
            } else {
                dialPaint.setColor(fanOffColor);
            }
            // Redraw the view.
            invalidate();
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = (float) (Math.max(width, height) / 2 * 0.8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw the dial.
        canvas.drawCircle(width / 2, height / 2, radius, dialPaint);
        // Draw the text labels.
        final float labelRadius = radius + 20;
        StringBuffer label = mTempLabel;
        for (int i = 0; i < selectionCount; i++) {
            float[] xyData = computeXYForPosition(i, labelRadius, true);
            float x = xyData[0];
            float y = xyData[1];
            label.setLength(0);
            label.append(i);
            canvas.drawText(label, 0, label.length(), x, y, textPaint);
        }
        // Draw the indicator mark.
        final float markerRadius = radius - 35;
        float[] xyData = computeXYForPosition(activeSelection,
                markerRadius, false);
        float x = xyData[0];
        float y = xyData[1];
        canvas.drawCircle(x, y, 20, textPaint);
    }

    private float[] computeXYForPosition(final int pos, final float radius, boolean isLabel) {
        float[] result = mTempResult;
        Double startAngle;
        Double angle;
        if (selectionCount > 4) {
            startAngle = Math.PI * (3 / 2d);
            angle = startAngle + (pos * (Math.PI / selectionCount));
            result[0] = (float) (radius * Math.cos(angle * 2))
                    + (width / 2);
            result[1] = (float) (radius * Math.sin(angle * 2))
                    + (height / 2);
            if ((angle > Math.toRadians(360)) && isLabel) {
                result[1] += 20;
            }
        } else {
            startAngle = Math.PI * (9 / 8d);
            angle = startAngle + (pos * (Math.PI / selectionCount));
            result[0] = (float) (radius * Math.cos(angle))
                    + (width / 2);
            result[1] = (float) (radius * Math.sin(angle))
                    + (height / 2);
        }
        return result;
    }
}
