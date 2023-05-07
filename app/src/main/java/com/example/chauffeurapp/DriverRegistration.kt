package com.example.chauffeurapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chauffeurapp.databinding.ActivityDriverRegistrationBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DriverRegistration : AppCompatActivity() {

    private lateinit var binding: ActivityDriverRegistrationBinding
    private lateinit var database: DatabaseReference

    // Function to check if email is valid
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDriverRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Driver Registration"

        database = FirebaseDatabase.getInstance().getReference("DriverUsers")

        binding.registerBtn.setOnClickListener {
            val driverName = binding.dNameEt.text.toString()
            val email = binding.dMailEt.text.toString()
            val phoneNumber = binding.dPhoneEt.text.toString()
            val password = binding.dPassEt.text.toString()
            val confirmPassword = binding.dConPassEt.text.toString()



            // Check if driver name is empty or invalid
            if (driverName.isEmpty() || !driverName.matches(Regex("^[A-Z][a-zA-Z ]*[a-zA-Z]\$"))) {
                binding.dNameEt.error = "Driver name is required and must start with a capital letter and contain only letters and spaces"
                return@setOnClickListener
            }

// Check if the driver name already exists in the database
            val driverNameQuery = database.orderByChild("driverName").equalTo(driverName)
            driverNameQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        binding.dNameEt.error = "Driver name already exists"
                        return
                    } else {
                        // The driver name is unique and valid, continue with registration
                        // Check other fields

                        // Check if email is empty
                        if (email.isEmpty()) {
                            binding.dMailEt.error = "Email is required"
                            return
                        }

                        // Check if email is valid
                        if (!isEmailValid(email)) {
                            binding.dMailEt.error = "Invalid email"
                            return
                        }

                        val emailQuery = database.orderByChild("email").equalTo(email)
                        emailQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    binding.dMailEt.error = "Email already exists"
                                    return
                                } else {
                                    // The email is unique, continue with registration

                                    // Check if phone number is empty
                                    if (phoneNumber.isEmpty()) {
                                        binding.dPhoneEt.error = "Phone number is required"
                                        return
                                    }

                                    // Check if password is empty
                                    if (password.isEmpty()) {
                                        binding.dPassEt.error = "Password is required"
                                        return
                                    }

                                    // Check if confirm password is empty
                                    if (confirmPassword.isEmpty()) {
                                        binding.dConPassEt.error = "Confirm password is required"
                                        return
                                    }

                                    // Check if password and confirm password match
                                    if (password != confirmPassword) {
                                        binding.dConPassEt.error = "Passwords don't match"
                                        return
                                    }

                                    // Check if password is at least 6 characters long
                                    if (password.length < 6) {
                                        binding.dPassEt.error = "Password must be at least 6 characters long"
                                        return
                                    }

                                    // All fields are valid, save data to the database
                                    val driverUser = DriverUser(driverName, email, phoneNumber, password, confirmPassword)

                                    database.child(driverName).setValue(driverUser)
                                        .addOnSuccessListener {
                                            binding.dNameEt.text?.clear()
                                            binding.dMailEt.text?.clear()
                                            binding.dPhoneEt.text?.clear()
                                            binding.dPassEt.text?.clear()
                                            binding.dConPassEt.text?.clear()

                                            Toast.makeText(this@DriverRegistration, "Successfully saved", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(this@DriverRegistration, "Failed to save data: ${it.message}", Toast.LENGTH_SHORT).show()
                                        }
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Handle errors
                                Toast.makeText(this@DriverRegistration, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                    Toast.makeText(this@DriverRegistration, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })


        }
    }
}
