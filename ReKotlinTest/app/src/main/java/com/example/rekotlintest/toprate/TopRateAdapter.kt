package com.example.rekotlintest.toprate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rekotlintest.R
import com.example.rekotlintest.core.ItemInteractor
import com.example.rekotlintest.data.Movie
import kotlinx.android.synthetic.main.fragment_tab_top_rate_item.view.*

class TopRateAdapter(private val listener: ItemInteractor<Movie>)
    : RecyclerView.Adapter<TopRateAdapter.TopRateVH>() {
    private var data: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRateVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_tab_top_rate_item, null)
        return TopRateVH(itemView)
    }

    fun update(data: ArrayList<Movie>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: TopRateVH, position: Int) {
        val movie = data[position]
        holder.itemView.apply {
            title.text = movie.title
            desc.text = movie.overview
            setOnClickListener { listener.onItemClick(data[position]) }
        }
    }

    class TopRateVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}