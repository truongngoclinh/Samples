package com.linhtruong.samplekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.linhtruong.samplekt.ext.addFragment
import com.linhtruong.samplekt.textwatermaskbitmap.TextWatermaskBitmapFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.simple_list_item.view.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val data = arrayOf("TextWatermaskBitMap", "etc")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.apply {
            adapter = SimpleAdapter(data) { pos ->
                when (pos) {
                    0 -> addFragment(TextWatermaskBitmapFragment())
                }
            }
        }
    }

    private class SimpleAdapter(
        private val data: Array<String>,
        private val click: (Int) -> Unit
    ) :
        RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
            holder.itemView.title.text = data[position]
            holder.itemView.setOnClickListener { click(position) }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder =
            SimpleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.simple_list_item, parent, false)
            )

        class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }
}
