package madproject.visitsrilanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import madproject.visitsrilanka.databinding.ActivityGuideregisterBinding

class guideregister : AppCompatActivity() {

    private lateinit var binding: ActivityGuideregisterBinding
    private lateinit var database: DatabaseReference

    //email valid
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_guideregister)

        binding= ActivityGuideregisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Guide Registration"

        database = FirebaseDatabase.getInstance().getReference("Guides")

        binding.addBtn.setOnClickListener{

            val name = binding.name.text.toString()
            val district = binding.district.text.toString()
            val email = binding.email.text.toString()
            val number = binding.number.text.toString()
            val age = binding.age.text.toString()
            val gender = binding.gender.text.toString()

            //check
            if (name.isEmpty() || !name.matches(Regex("^[A-Z][a-zA-Z ]*[a-zA-Z]\$"))) {
                binding.name.error = "Driver name is required and must start with a capital letter and contain only letters and spaces"
                return@setOnClickListener
            }

            //check if already name in the database
            val driverNameQuery = database.orderByChild("Guides").equalTo(name)
            driverNameQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        binding.name.error = "Guide name already exists"
                        return
                    } else {
                        // Check other fields

                        // Check if email is empty
                        if (name.isEmpty()) {
                            binding.name.error = "Name is required"
                            return
                        }
                        // Check if email is valid
                        if (!isEmailValid(email)) {
                            binding.email.error = "Invalid email Address"
                            return
                        }

                        val emailQuery = database.orderByChild("email").equalTo(email)
                        emailQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    binding.email.error = "Email already exists"
                                    return
                                } else {
                                    // The email is unique, continue with registration

                                    // Check if phone number is empty
                                    if (name.isEmpty()) {
                                        binding.name.error = "Name number is required"
                                        return
                                    }

                                    // Check if password is empty
                                    if (email.isEmpty()) {
                                        binding.email.error = "Email is required"
                                        return
                                    }

                                    //Check if confirm password is empty
                                    if (district.isEmpty()) {
                                        binding.district.error = "District is required"
                                        return
                                    }
                                    if (gender.isEmpty()) {
                                        binding.gender.error = "Gender is required"
                                        return
                                    }
                                    if (age.isEmpty()) {
                                        binding.age.error = "age required"
                                        return
                                    }
                                    // Check if password is at least 6 characters long
                                    if (number.length < 10) {
                                        binding.number.error = "number least 6 characters long"
                                        return
                                    }

                                    // All fields are valid, save data to the database
                                    val Guide = Guide(name, email, district, number, age, gender)

                                    database.child(name).setValue(Guide).addOnSuccessListener {

                                        binding.name.text.clear()
                                        binding.district.text.clear()
                                        binding.email.text.clear()
                                        binding.number.text.clear()
                                        binding.age.text.clear()
                                        binding.gender.text.clear()

                                        Toast.makeText(
                                            this@guideregister,
                                            "Successfully Saved Guide details ",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                        .addOnFailureListener {
                                            Toast.makeText(
                                                this@guideregister,
                                                "Failed to save data: ${it.message}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Handle errors
                                Toast.makeText(
                                    this@guideregister,
                                    "Error: ${databaseError.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                    Toast.makeText(this@guideregister, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })


        }
    }
}

