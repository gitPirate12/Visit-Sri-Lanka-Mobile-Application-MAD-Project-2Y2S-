package com.example.chauffeurapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DriverRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_registration)
        title = "Driver Registration"
    }
}