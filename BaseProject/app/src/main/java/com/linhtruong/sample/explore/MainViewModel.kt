package com.linhtruong.sample.explore

import android.arch.lifecycle.MutableLiveData
import com.linhtruong.sample.core.platform.base.BaseViewModel
import com.linhtruong.sample.explore.model.NewsDetailEntity
import com.linhtruong.sample.explore.model.NewsEntity
import com.linhtruong.sample.explore.repo.MainRepository
import com.linhtruong.sample.explore.model.NewsResponse
import timber.log.Timber
import javax.inject.Inject


/**
 * @author linhtruong
 */
class MainViewModel
@Inject constructor(private val repository: MainRepository) : BaseViewModel() {
    var news = MutableLiveData<ArrayList<NewsEntity>>()
    fun getNews() {
        loadingStatus.postValue(true)
        addDisposable(repository.getNews { it.either(::handleFailure, ::handleNewsResponse) })
    }

    private fun handleNewsResponse(response: NewsResponse) {
        news.postValue(response.news)
        loadingStatus.postValue(false)
    }

    var newsDetail = MutableLiveData<NewsDetailEntity>()
    fun updateDetail(detail: NewsDetailEntity) {
        Timber.d("updateDetail postValue")
        newsDetail.postValue(detail)
    }
}