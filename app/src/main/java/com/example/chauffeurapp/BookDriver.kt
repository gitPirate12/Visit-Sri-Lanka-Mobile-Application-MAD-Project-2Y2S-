package com.example.chauffeurapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BookDriver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_driver)
        title = "Book Now"
    }
}