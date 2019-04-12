package com.linhtruong.sample.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.linhtruong.sample.R
import kotlinx.android.synthetic.main.fragment_news_item.view.*
import com.linhtruong.sample.core.extension.empty
import com.linhtruong.sample.core.platform.base.BaseRecyclerAdapter
import com.linhtruong.sample.core.platform.interactor.ItemInteractor
import com.linhtruong.sample.explore.model.NewsEntity


/**
 * @author linhtruong
 */
class NewsListAdapter constructor(private val listener: ItemInteractor<NewsEntity>) : BaseRecyclerAdapter<NewsEntity, NewsListAdapter.NewsHolder>() {
    override fun createHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_news_item, parent, false)
        return NewsHolder(view, listener)
    }

    inner class NewsHolder(itemView: View, listener: ItemInteractor<NewsEntity>) : BaseRecyclerAdapter.ViewHolder<NewsEntity>(itemView) {
        init {
            itemView.setOnClickListener { listener.onItemClick(data[adapterPosition]) }
        }

        override fun bindData(data: NewsEntity, position: Int) {
            itemView.apply {
                title.text = data.title

                data.multimedia?.let {
                    val url = if (it.size > 0) it[0].url else String.empty()
                    Glide.with(context)
                            .load(url)
                            .thumbnail(0.7f)
                            .transition(GenericTransitionOptions.with(R.anim.anim_showing_image))
                            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_place_holder))
                            .into(thumbnail)
                }
            }
        }
    }
}