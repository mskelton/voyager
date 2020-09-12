package com.markskelton.voyager.ui.log.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.markskelton.voyager.databinding.ActivityCreateLogBinding

class CreateLogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
