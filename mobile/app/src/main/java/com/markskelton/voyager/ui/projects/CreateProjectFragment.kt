package com.markskelton.voyager.ui.projects

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.markskelton.voyager.R

typealias OnCreateProjectListener = (name: String) -> Unit

class CreateProjectFragment : DialogFragment() {
    private lateinit var onCreateProjectListener: OnCreateProjectListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_name, null)

            builder
                .setTitle(R.string.create_project)
                .setView(view)
                .setPositiveButton(R.string.create) { _, _ ->
                    val nameView = view.findViewById<TextView>(R.id.dialog_name_input)
                    onCreateProjectListener(nameView.text.toString())
                }
                .setNegativeButton(R.string.cancel, null)

            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun setOnCreateProjectListener(listener: OnCreateProjectListener) {
        onCreateProjectListener = listener
    }
}
