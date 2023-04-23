package com.example.chauffeurapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        title = "Edit Profile"
    }
}