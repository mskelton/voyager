package com.markskelton.voyager.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.markskelton.voyager.R

class SimpleListAdapter : RecyclerView.Adapter<SimpleListAdapter.SimpleListViewHolder>() {
    var entities: List<String> = ArrayList()

    class SimpleListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleListViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return SimpleListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SimpleListViewHolder, position: Int) {
        val nameView = holder.view.findViewById<TextView>(R.id.text_view)
        nameView.text = entities[position]
    }

    override fun getItemCount() = entities.size
}
