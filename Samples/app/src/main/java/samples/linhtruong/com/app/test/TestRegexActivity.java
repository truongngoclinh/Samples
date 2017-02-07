package samples.linhtruong.com.app.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.utils.RegexUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/7/17 - 11:03.
 * @organization VED
 */

@EActivity
public class TestRegexActivity extends Activity {

    private final String regexSample1 = "abc1234xyz000 **9r ajz.";
    public static final String regexSample = "This is my small example "
            + "string which I'm going to " + "use for pattern matching.";


    @ViewById(R.id.regexText)
    TextView mRegexText;

    @ViewById(R.id.regexSample)
    TextView mRegexSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_regex_test);
    }

    @AfterViews
    void afterView() {
        mRegexSample.setText(regexSample);

        String regexStr;
//        regexStr = RegexUtils.regexNumber(regexSample);
        regexStr = RegexUtils.regexText(regexSample1);
//        regexStr = RegexUtils.regexWord(regexSample);
//        regexStr = RegexUtils.regexNonword(regexSample);
//        regexStr = RegexUtils.regexLetter(regexSample);

        RegexUtils.regexCombine1(regexSample);

        boolean isMatched;
        isMatched = RegexUtils.isLessThenThreeHundred("aas128 asda");

        System.out.println(regexStr);
        System.out.println(isMatched);

        mRegexText.setText(regexStr);
    }
}
