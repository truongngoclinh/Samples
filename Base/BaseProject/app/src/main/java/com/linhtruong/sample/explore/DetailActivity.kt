package com.linhtruong.sample.explore

import android.content.Intent
import com.linhtruong.sample.R
import com.linhtruong.sample.core.platform.base.BaseActivity
import com.linhtruong.sample.core.platform.base.BaseFragment
import com.linhtruong.sample.core.platform.base.BaseFragmentActivity
import com.linhtruong.sample.explore.model.NewsDetailEntity


/**
 * @author linhtruong
 */
class DetailActivity : BaseFragmentActivity() {
    companion object {
        private const val KEY_NEWS_DETAIL = "key_news_detail"

        fun forDetail(activity: BaseActivity, newsDetail: NewsDetailEntity): Intent {
            var intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(KEY_NEWS_DETAIL, newsDetail)
            return intent
        }
    }

    override fun fragment(): BaseFragment {
        val newsDetail = intent.getParcelableExtra<NewsDetailEntity>(KEY_NEWS_DETAIL)
        return NewsDetailFragment.forDetail(newsDetail)
    }

    override fun title(): Int = R.string.label_app_name
    override fun enableBack(): Boolean = true
}