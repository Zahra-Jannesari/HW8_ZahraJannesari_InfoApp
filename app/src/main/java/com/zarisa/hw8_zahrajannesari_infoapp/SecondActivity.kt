package com.zarisa.hw8_zahrajannesari_infoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zarisa.hw8_zahrajannesari_infoapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}