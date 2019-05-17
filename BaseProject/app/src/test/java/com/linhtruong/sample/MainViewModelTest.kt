package com.linhtruong.sample

import com.linhtruong.sample.core.exception.Failure
import com.linhtruong.sample.core.helper.NetworkHelper
import com.linhtruong.sample.explore.MainViewModel
import com.linhtruong.sample.explore.model.NewsEntity
import com.linhtruong.sample.explore.model.NewsResponse
import com.linhtruong.sample.explore.repo.MainRepository
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Observable
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.mockito.*
import java.lang.NumberFormatException


/**
 * @author linhtruong
 */
class MainViewModelTest : UnitTest() {
    lateinit var mainViewModel: MainViewModel
    @Mock
    private lateinit var mainRepository: MainRepository
    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(mainRepository)
        mainViewModel.networkHelper = networkHelper
    }

     /**
     * Using official mockito api
     */
    @Test
    fun requestNews_loadingIsShown() {
        Mockito.`when`(networkHelper.isConnected()).thenReturn(true)
        Mockito.`when`(mainRepository.getNews()).thenAnswer { Observable.just(NewsResponse()) }
        mainViewModel.loadingStatus.observeForever {
            assert(it == true)
        }
        mainViewModel.requestNews()
    }


    /**
     * Using kluent to make assert more concise
     */
    @Test
    fun requestNews_newsLiveDataShouldBeUpdated() {
        val news1 = NewsEntity("1", "", "", null)
        val news2 = NewsEntity("2", "", "", null)
        val newsArray = ArrayList<NewsEntity>()
        newsArray.add(news1)
        newsArray.add(news2)
        given(networkHelper.isConnected()).willReturn(true)
        given(mainRepository.getNews()).willReturn(Observable.just(NewsResponse(newsArray)))
        mainViewModel.news.observeForever {
            it `should not be` null
            it?.size `should equal` 2
            it?.get(0)?.title `should equal`  "1"
        }
        mainViewModel.requestNews()
    }

    @Test
    fun requestNews_success_loadingShouldHide() {
        given(networkHelper.isConnected()).willReturn(true)
        given(mainRepository.getNews()).willReturn(Observable.just(NewsResponse()))
        mainViewModel.loadingStatus.observeForever {
            it shouldBe false
        }
        mainViewModel.requestNews()
    }

    @Test
    fun requestNews_exception_error() {
        given(networkHelper.isConnected()).willReturn(true)
        given(mainRepository.getNews()).willReturn(Observable.error(NumberFormatException()))
        mainViewModel.requestNews()
        mainViewModel.failure.observeForever {
            it shouldBeInstanceOf  Failure.ExceptionError::class.java
        }
    }

    @Test
    fun requestNews_no_network_connection() {
        given(networkHelper.isConnected()).willReturn(false)
        given(mainRepository.getNews()).willReturn(Observable.just(NewsResponse()))
        mainViewModel.requestNews()
        mainViewModel.failure.observeForever {
            it shouldBeInstanceOf  Failure.NetworkError::class.java
        }
    }
}