package com.example.chauffeurapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chauffeurapp.databinding.ActivityDeleteBinding

import com.google.firebase.database.*

class Delete : AppCompatActivity() {

    private lateinit var binding:ActivityDeleteBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Delete Driver"

        binding.driverDeleteBtn.setOnClickListener {
            val driverName = binding.deleteDriverET.text.toString()
            if(driverName.isNotEmpty()){
                deleteData(driverName)
            }
            else {
                Toast.makeText(this, "Please enter the Driver name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(driverName: String) {
        database = FirebaseDatabase.getInstance().getReference("DriverUsers")
        database.child(driverName).removeValue().addOnSuccessListener {
            binding.deleteDriverET.text?.clear()
            Toast.makeText(this, "Successfully deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
