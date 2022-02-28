package com.zarisa.hw8_zahrajannesari_infoapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.core.text.isDigitsOnly
import com.zarisa.hw8_zahrajannesari_infoapp.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    lateinit var binding:ActivityFirstBinding
    private var sharedPreferences: SharedPreferences? = null
    private var infoList= listOf(binding.textFieldFullName,binding.textFieldID,binding.textFieldBirthday
                                ,binding.textFieldAddress,binding.textFieldZipCode)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFirstBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        sharedPreferences = getSharedPreferences("Information", Context.MODE_PRIVATE)
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
        var result=true
       infoList.forEach{
           if(it.text.isBlank()) {
               it.error ="تکمیل فیلد اجباری است!"
               result= false
           }
       }
        if(!binding.textFieldID.text.isDigitsOnly()&&binding.textFieldID.text.length!=10){
            binding.textFieldID.error="کد ملی صحیح نمی باشد!"
            result= false
        }
        if(!binding.textFieldZipCode.text.isDigitsOnly()){
            binding.textFieldID.error="کد پستی صحیح نمی باشد!"
            result= false
        }
        return result
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val editor = sharedPreferences?.edit()
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

