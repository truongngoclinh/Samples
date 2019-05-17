package com.linhtruong.sample

import com.linhtruong.sample.explore.MainActivity
import com.linhtruong.sample.explore.NewsListFragment
import org.robolectric.android.controller.ActivityController


/**
 * @author linhtruong
 *
 * TODO: problem with inflating fragment inside xml file
 */
class MainActivityTest : AndroidTest() {

    private lateinit var activityController: ActivityController<MainActivity>
    private lateinit var fragment: NewsListFragment
/*
    @Before
    fun setUp() {
        fragment = NewsListFragment()
        activityController = Robolectric.buildActivity(MainActivity::class.java)
        activityController.create().start().resume()
        activityController.get().supportFragmentManager.beginTransaction().add(fragment, null).commit()
    }

    @Test
    fun activityNotNull() {
        activityController.get() shouldNotBe null
    }*/
}