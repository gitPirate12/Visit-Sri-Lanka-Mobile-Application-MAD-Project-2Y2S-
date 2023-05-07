package com.example.chauffeurapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Main Menu"

        val DeleteBtn = findViewById<Button>(R.id.DeleteDriverBtn)
        DeleteBtn.setOnClickListener {
            val intent = Intent(this, Delete::class.java)
            startActivity(intent)
        }

        val editProfileBtn = findViewById<Button>(R.id.editProfileBtn)
        editProfileBtn.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }

        val registerBtn = findViewById<Button>(R.id.MainmenuRegisterBtn)
        registerBtn.setOnClickListener {
            val intent = Intent(this, DriverRegistration::class.java)
            startActivity(intent)
        }

        val bookNowBtn = findViewById<Button>(R.id.findDriverBtn)
        bookNowBtn.setOnClickListener {
            val intent = Intent(this, ReadDriver::class.java)
            startActivity(intent)
        }
    }
}
