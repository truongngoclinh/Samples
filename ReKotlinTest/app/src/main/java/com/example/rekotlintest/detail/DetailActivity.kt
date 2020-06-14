package com.example.rekotlintest.detail

import android.os.Bundle
import com.example.rekotlintest.R
import com.example.rekotlintest.core.base.BaseActivity
import com.example.rekotlintest.data.Movie
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class DetailActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()

    companion object {
        const val KEY_MOVIE = "key_movie"
    }

    private val moshi: Moshi by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val strMovie = intent.getStringExtra(KEY_MOVIE)
        val jsonAdapter = moshi.adapter(Movie::class.java)
        val movie = jsonAdapter.fromJson(strMovie)

        txtDesc.text = movie?.overview
        txtTitle.text = movie?.title
    }
}