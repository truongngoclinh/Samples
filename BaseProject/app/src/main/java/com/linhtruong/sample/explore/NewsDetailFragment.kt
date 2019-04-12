package com.linhtruong.sample.explore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.linhtruong.sample.R
import kotlinx.android.synthetic.main.fragment_news_detail.*
import com.linhtruong.sample.core.extension.*
import com.linhtruong.sample.core.network.NetworkConst
import com.linhtruong.sample.core.platform.base.BaseFragment
import com.linhtruong.sample.explore.model.NewsDetailEntity
import timber.log.Timber


/**
 * @author linhtruong
 */
class NewsDetailFragment : BaseFragment() {
    companion object {
        private const val KEY_NEWS_DETAIL = "key_news_detail"

        fun forDetail(newsDetail: NewsDetailEntity): NewsDetailFragment {
            var fragment = NewsDetailFragment()
            var bundle = Bundle()
            bundle.putParcelable(KEY_NEWS_DETAIL, newsDetail)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var mainViewModel: MainViewModel

    override fun layoutId(): Int = R.layout.fragment_news_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        mainViewModel = viewModel(viewModelFactory) {
            observe(newsDetail, ::updateNewsDetail)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val newsDetail = it.getParcelable<NewsDetailEntity>(KEY_NEWS_DETAIL)
            newsDetail?.let { detail ->
                updateViews(detail)
            }
        }
    }

    private fun updateNewsDetail(newsDetail: NewsDetailEntity?) {
        Timber.d("updateNewsDetail invoke()")
        newsDetail?.let {
            updateViews(it)
        }
    }

    private fun updateViews(newsDetail: NewsDetailEntity) {
        txtTitle.text = newsDetail.title
        txtSummary.text = newsDetail.summary

        Glide.with(this)
                .load(newsDetail.imgUrl)
                .thumbnail(0.7f)
                .transition(GenericTransitionOptions.with(R.anim.anim_showing_image))
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_place_holder))
                .into(imgNews)

        btnStoryLink.setOnClickListener {
            if (newsDetail.storyUrl!!.startsWith(NetworkConst.PREFIX_HTTP)) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(newsDetail.storyUrl)
                startActivity(intent)
            } else {
                baseActivity.notify(R.string.error_story_link_invalid, Snackbar.LENGTH_LONG)
            }
        }
    }
}