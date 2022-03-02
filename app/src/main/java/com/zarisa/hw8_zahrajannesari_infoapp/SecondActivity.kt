package com.zarisa.hw8_zahrajannesari_infoapp


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zarisa.hw8_zahrajannesari_infoapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    private var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("Information", Context.MODE_PRIVATE)
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        binding.textViewID.text="کد ملی:     ${sharedPreferences?.getString(ID,"")}"
        binding.textViewBirthday.text="محل تولد:     ${sharedPreferences?.getString(birthday,"")}"
        binding.textViewAddress.text="آدرس:     ${sharedPreferences?.getString(address,"")}"
        binding.textViewZipCode.text="کد پستی:     ${sharedPreferences?.getString(zipCode,"")}"
        if (sharedPreferences?.getString(Gender,"")=="Female")
            binding.textViewGender.text="جنسیت:     مونث"
        if (sharedPreferences?.getString(Gender,"")=="Male")
            binding.textViewGender.text="جنسیت:     مذکر"


        binding.buttonChangeInformation.setOnClickListener { goToFirstPage(true) }
        binding.buttonEnterNewUser.setOnClickListener { goToFirstPage(false) }
    }

    private fun goToFirstPage(isEdit: Boolean) {
        if (!isEdit){
            val editor = sharedPreferences?.edit()
            editor?.putString(fullName, "")
            editor?.apply()
            editor?.putString(ID, "")
            editor?.apply()
            editor?.putString(birthday, "")
            editor?.apply()
            editor?.putString(address, "")
            editor?.apply()
            editor?.putString(zipCode, "")
            editor?.apply()
            editor?.putString(Gender, "")
            editor?.apply()
        }
        val intent=Intent(this,FirstActivity::class.java)
        intent.putExtra(edit,isEdit)
        startActivity(intent)
    }

    override fun onBackPressed() {
        this.finishAffinity()
        super.onBackPressed()
    }
}