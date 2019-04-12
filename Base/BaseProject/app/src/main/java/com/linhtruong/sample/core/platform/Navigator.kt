package com.linhtruong.sample.core.platform


import android.app.Activity
import com.linhtruong.sample.R
import com.linhtruong.sample.core.platform.base.BaseActivity
import com.linhtruong.sample.explore.DetailActivity
import com.linhtruong.sample.explore.model.NewsDetailEntity
import javax.inject.Inject


/**
 * @author linhtruong
 */
class Navigator @Inject constructor() {
    private fun activityTransitionSlide(activity: Activity) {
        activity.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left)
    }

    fun openDetailActivity(activity: BaseActivity, newsDetail: NewsDetailEntity) {
        activity.startActivity(DetailActivity.forDetail(activity, newsDetail))
        activityTransitionSlide(activity)
    }
}

