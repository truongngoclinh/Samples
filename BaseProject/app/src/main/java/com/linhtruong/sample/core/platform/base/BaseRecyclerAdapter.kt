package com.linhtruong.sample.core.platform.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

/**
 * @author linhtruong
 */

abstract class BaseRecyclerAdapter<T, H : BaseRecyclerAdapter.ViewHolder<T>> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: ArrayList<T> = ArrayList()

    fun add(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createHolder(parent, viewType)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = data[position]
        (holder as ViewHolder<T>).bindData(data, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    abstract fun createHolder(parent: ViewGroup, viewType: Int): H

    abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindData(data: T, position: Int)
    }
}
