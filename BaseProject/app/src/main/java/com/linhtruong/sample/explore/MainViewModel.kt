package com.linhtruong.sample.explore

import android.arch.lifecycle.MutableLiveData
import com.linhtruong.sample.core.exception.Failure
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
    fun requestNews() {
        loadingStatus.postValue(true)
        addDisposable(repository.getNews().subscribe(
                {
                    handleNewsResponse(it)
                },
                {
                    handleFailure(Failure.ExceptionError(it))
                }
        ))
    }

    private fun handleNewsResponse(response: NewsResponse) {
        news.postValue(response.news)
        loadingStatus.postValue(false)
    }

    var newsDetail = MutableLiveData<NewsDetailEntity>()
    fun updateDetail(detail: NewsDetailEntity) {
        newsDetail.postValue(detail)
    }
}