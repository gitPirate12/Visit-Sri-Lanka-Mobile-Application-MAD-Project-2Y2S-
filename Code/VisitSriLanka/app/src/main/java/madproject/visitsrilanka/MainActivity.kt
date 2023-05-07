package com.example.guide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        title="Guide menu"

        val seebutton=findViewById<Button>(R.id.seebutton)
        seebutton.setOnClickListener{
            val intent=Intent(this,
                GuideDash::class.java)
            startActivity(intent)
        }

        val addbutton=findViewById<Button>(R.id.addguide)
        addbutton.setOnClickListener{
            val intent=Intent(this,
            GuideRegister::class.java)
            startActivity(intent)
        }

        val findButton=findViewById<Button>(R.id.findbtn)
            findButton.setOnClickListener{
            val intent=Intent(this,
                ReadGuide::class.java)
            startActivity(intent)
        }

        val updateguidebutton=findViewById<Button>(R.id.updateguidebutton)
            updateguidebutton.setOnClickListener{
            val intent=Intent(this,
                UpdateGuide::class.java)
            startActivity(intent)
        }

        val deleteguidebutton=findViewById<Button>(R.id.deletebtn)
            deleteguidebutton.setOnClickListener{
            val intent=Intent(this,
                DeleteGuide::class.java)
            startActivity(intent)
        }



    }


}