package com.markskelton.voyager.ui.log.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.markskelton.voyager.databinding.FragmentCreateLogBinding

class CreateLogFragment : Fragment() {
    private lateinit var binding: FragmentCreateLogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateLogBinding.inflate(layoutInflater)
        return binding.root
    }
}
