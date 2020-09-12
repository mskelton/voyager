package com.markskelton.voyager.ui.logs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.markskelton.voyager.R
import com.markskelton.voyager.db.models.HydratedLog

class LogListAdapter : RecyclerView.Adapter<LogListAdapter.LogListViewHolder>() {
    var entities: List<HydratedLog> = ArrayList()

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class LogListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LogListViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.log_list_item, parent, false)

        return LogListViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: LogListViewHolder, position: Int) {
        val projectView = holder.view.findViewById<TextView>(R.id.project_name)
        val totalMilesView = holder.view.findViewById<TextView>(R.id.total_miles)
        val vehicleView = holder.view.findViewById<TextView>(R.id.vehicle_name)

        val context = holder.itemView.context
        val entity = entities[position]
        val totalMiles = entity.log.endMileage - entity.log.startMileage

        // Update the list item with the new data
        projectView.text = entity.project.name
        vehicleView.text = entity.vehicle.name
        // totalMilesView.text = context.getString(R.string.log_list_total_miles, totalMiles)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = entities.size
}
