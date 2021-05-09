package com.example.javaquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startbtn.setOnClickListener(){
        if(editTextbtn.text.toString().isEmpty())
        {
           Toast.makeText(this,"Name cannot be empty",Toast.LENGTH_SHORT).show()
        }
            else
        {
            val intent=Intent(this,QuestionActivity::class.java)
            startActivity(intent)
            finish()
        }
        }

    }
}