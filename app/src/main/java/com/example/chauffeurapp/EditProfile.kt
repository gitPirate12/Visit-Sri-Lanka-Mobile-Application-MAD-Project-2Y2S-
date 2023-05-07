package com.example.chauffeurapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chauffeurapp.databinding.ActivityEditProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditProfile : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveChangesBtn.setOnClickListener {

            val driverName = binding.editDriverNameET.text.toString()
            val email = binding.editEmailET.text.toString()
            val phoneNumber = binding.editPhoneET.text.toString()
            val password = binding.editPassET.text.toString()
            val confirmPassword = binding.editConPassET.text.toString()

            updateData(driverName,email,phoneNumber,password,confirmPassword)

        }

    }

    private fun updateData(
        driverName: String,
        email: String,
        phoneNumber: String,
        password: String,
        confirmPassword: String
    ) {

        // Check if driver name is empty
        if (driverName.isEmpty()) {
            binding.editDriverNameET.error = "Driver name is required"
            return
        }

// Check if driver name is valid
        val driverNameRegex = Regex("^[A-Z][a-zA-Z]*\\s[a-zA-Z]*\$")
        if (!driverName.matches(driverNameRegex)) {
            binding.editDriverNameET.error = "Invalid driver name"
            return
        }

        // Check if email is empty
        if (email.isEmpty()) {
            binding.editEmailET.error = "Email is required"
            return
        }

        // Check if email is valid
        if (!isEmailValid(email)) {
            binding.editEmailET.error = "Invalid email"
            return
        }

        // Check if phone number is empty
        if (phoneNumber.isEmpty()) {
            binding.editPhoneET.error = "Phone number is required"
            return
        }

        // Check if password is empty
        if (password.isEmpty()) {
            binding.editPassET.error = "Password is required"
            return
        }

        // Check if confirm password is empty
        if (confirmPassword.isEmpty()) {
            binding.editConPassET.error = "Confirm password is required"
            return
        }

        // Check if password and confirm password match
        if (password != confirmPassword) {
            binding.editConPassET.error = "Passwords do not match"
            return
        }

        // Initialize database reference
        database = FirebaseDatabase.getInstance().getReference("DriverUsers")



        // Create user object
        val user = mapOf<String,String>(
            "driverName" to driverName,
            "email" to email,
            "phoneNumber" to phoneNumber,
            "password" to password,
            "confirmPassword" to confirmPassword
        )

        // Update user data in database
        database.child(driverName).updateChildren(user).addOnSuccessListener {

            // Clear fields and show success message
            binding.editDriverNameET.text?.clear()
            binding.editEmailET.text?.clear()
            binding.editPhoneET.text?.clear()
            binding.editPassET.text?.clear()
            binding.editConPassET.text?.clear()
            Toast.makeText(this,"Successfully Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{

            // Show failure message
            Toast.makeText(this,"Failed to Update", Toast.LENGTH_SHORT).show()

        }
    }
}
fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}