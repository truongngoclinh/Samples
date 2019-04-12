package com.linhtruong.sample.explore

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.linhtruong.sample.R
import kotlinx.android.synthetic.main.fragment_news_list.*
import com.linhtruong.sample.core.extension.*
import com.linhtruong.sample.core.platform.base.BaseFragment
import com.linhtruong.sample.core.platform.interactor.ItemInteractor
import com.linhtruong.sample.explore.model.NewsDetailEntity
import com.linhtruong.sample.explore.model.NewsEntity


/**
 * @author linhtruong
 */
class NewsListFragment : BaseFragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var newsAdapter: NewsListAdapter

    override fun layoutId(): Int = R.layout.fragment_news_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        mainViewModel = viewModel(viewModelFactory) {
            failure(failure, ::renderFailure)
            loading(loadingStatus, ::renderLoading)
            observe(news, ::handleNews)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNewsList()
        mainViewModel.getNews()
    }

    private fun handleNews(news: ArrayList<NewsEntity>?) {
        news?.let {
            newsAdapter.add(it)
        }
    }

    private fun initNewsList() {
        newsAdapter = NewsListAdapter(object : ItemInteractor<NewsEntity> {
            override fun onItemClick(data: NewsEntity) {
                var imgUrl = String.empty()
                data.multimedia?.let {
                    if (it.size > 0) {
                        imgUrl = it[it.size - 1].url
                    }
                }

                val newsDetail = NewsDetailEntity(data.title, data.abstract, imgUrl, data.url)
                if (baseActivity.isTabletLandscapeMode()) {
                    mainViewModel.updateDetail(newsDetail)
                } else {
                    navigator.openDetailActivity(baseActivity, newsDetail)
                }
            }
        })
        rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}