package com.zarisa.hw8_zahrajannesari_infoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zarisa.hw8_zahrajannesari_infoapp.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    lateinit var binding:ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFirstBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
    }
}