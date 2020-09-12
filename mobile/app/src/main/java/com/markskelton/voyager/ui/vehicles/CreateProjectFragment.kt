package com.markskelton.voyager.ui.vehicles

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.markskelton.voyager.R

typealias OnCreateVehicleListener = (name: String) -> Unit

class CreateVehicleFragment : DialogFragment() {
    private lateinit var onCreateVehicleListener: OnCreateVehicleListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_name, null)

            builder
                .setTitle(R.string.create_vehicle)
                .setView(view)
                .setPositiveButton(R.string.create) { _, _ ->
                    val nameView = view.findViewById<TextView>(R.id.dialog_name_input)
                    onCreateVehicleListener(nameView.text.toString())
                }
                .setNegativeButton(R.string.cancel, null)

            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun setOnCreateVehicleListener(listener: OnCreateVehicleListener) {
        onCreateVehicleListener = listener
    }
}
