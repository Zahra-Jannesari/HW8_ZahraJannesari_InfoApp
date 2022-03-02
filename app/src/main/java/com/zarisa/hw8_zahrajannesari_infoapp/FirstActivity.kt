package com.zarisa.hw8_zahrajannesari_infoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.zarisa.hw8_zahrajannesari_infoapp.databinding.ActivityFirstBinding
import java.util.regex.Pattern

const val fullName="fullName"
const val ID="Id"
const val birthday="birthday"
const val address="address"
const val Gender="Gender"
const val zipCode="zipCode"
const val edit="Edit"
class FirstActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    private lateinit var binding:ActivityFirstBinding
    private var sharedPreferences: SharedPreferences? = null
    private var infoList= mutableSetOf<EditText>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFirstBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        sharedPreferences = getSharedPreferences("Information", Context.MODE_PRIVATE)
        initViews()
    }

    private fun initViews(){
        var editTime=intent.getBooleanExtra(edit,false)
        if (sharedPreferences?.getString(fullName, "")!=""&&!editTime){
            goToSecondPage()
        }
        if(editTime){
            putDataForEdit()
        }
        else {
            infoList = mutableSetOf(binding.textFieldFullName,binding.textFieldID,binding.textFieldBirthday,
                                    binding.textFieldAddress,binding.textFieldZipCode)
            binding.RadioGroupGender.setOnCheckedChangeListener(this)
            binding.buttonRegister.setOnClickListener {
                if (checkInfo()) {
                    saveInfoInShared()
                    goToSecondPage()
                }
            }
        }
    }

    private fun putDataForEdit() {
        binding.textFieldFullName.setText(sharedPreferences?.getString(fullName,""))
        binding.textFieldID.setText(sharedPreferences?.getString(ID,""))
        binding.textFieldBirthday.setText(sharedPreferences?.getString(birthday,""))
        binding.textFieldAddress.setText(sharedPreferences?.getString(address,""))
        binding.textFieldZipCode.setText(sharedPreferences?.getString(zipCode,""))
        when(sharedPreferences?.getString(Gender,"")){
            "Female"->binding.radioButtonFemale.isChecked=true
            "Male"->binding.radioButtonMale.isChecked=true
        }
    }

    private fun goToSecondPage() {
        val intent=Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }

    private fun saveInfoInShared() {
        val editor = sharedPreferences?.edit()
        editor?.putString(fullName, binding.textFieldFullName.text.toString())
        editor?.apply()
        editor?.putString(ID, binding.textFieldID.text.toString())
        editor?.apply()
        editor?.putString(birthday, binding.textFieldBirthday.text.toString())
        editor?.apply()
        editor?.putString(address, binding.textFieldAddress.text.toString())
        editor?.apply()
        editor?.putString(zipCode, binding.textFieldZipCode.text.toString())
        editor?.apply()
    }

    private fun checkInfo(): Boolean {
        var result=true
       infoList.forEach{
           if(it.text.isBlank()) {
               it.error ="تکمیل فیلد اجباری است!"
               result= false
           }
       }
        binding.textFieldID.text.let {

            if (it.isNotBlank() && (!it.toString().hasOnlyDigits() || it.length != 10)) {
                binding.textFieldID.error = "کد ملی صحیح نمی باشد!"
                result = false
            }
        }
        binding.textFieldZipCode.text.let{
            if (it.isNotBlank() && !it.toString().hasOnlyDigits()) {
                binding.textFieldZipCode.error = "کد پستی صحیح نمی باشد!"
                result = false
            }
        }
        if (sharedPreferences?.getString(Gender, "")==""){
            Toast.makeText(this, "Enter Gender!", Toast.LENGTH_SHORT).show()
            result=false
        }
        return result
    }

    private fun String?.hasOnlyDigits() : Boolean = try {
        if (this.isNullOrEmpty())
            false
        else Pattern.matches("[0-9]+", this)
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val editor = sharedPreferences?.edit()
        when(p1){
            R.id.radioButton_Female -> {
                editor?.putString(Gender, "Female")
                editor?.apply()
                return
            }
            R.id.radioButton_Male ->{
                editor?.putString(Gender,"Male")
                editor?.apply()
                return
            }
        }
    }
}


