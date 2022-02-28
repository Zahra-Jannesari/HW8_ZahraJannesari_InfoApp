package com.zarisa.hw8_zahrajannesari_infoapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import com.zarisa.hw8_zahrajannesari_infoapp.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    lateinit var binding:ActivityFirstBinding
    private var sharedpreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFirstBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        sharedpreferences = getSharedPreferences("Information", Context.MODE_PRIVATE)
        initViews()

    }
    private fun initViews(){
        binding.RadioGroupGender.setOnCheckedChangeListener(this)
        binding.buttonRegister.setOnClickListener {
            if(checkInfo()) {
                saveInfoInShared()
                goToSecondPage()
            }
        }
    }

    private fun goToSecondPage() {
        TODO("Not yet implemented")
    }

    private fun saveInfoInShared() {
        TODO("Not yet implemented")
    }

    private fun checkInfo(): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val editor = sharedpreferences?.edit()
        when(p1){
            R.id.radioButton_Female -> {
                editor?.putString("Gender", "Female")
                editor?.apply()
                return
            }
            R.id.radioButton_Male ->{
                editor?.putString("Gender","Male")
                editor?.apply()
                return
            }
        }
    }

}

