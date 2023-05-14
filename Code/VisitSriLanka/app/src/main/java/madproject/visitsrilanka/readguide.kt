package madproject.visitsrilanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import madproject.visitsrilanka.databinding.ActivityReadguideBinding

class readguide : AppCompatActivity() {

    private lateinit var binding: ActivityReadguideBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadguideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.guidereadbtn.setOnClickListener {
            val name: String = binding.searcguidename.text.toString()
            if (name.isNotEmpty()) {
                readData(name)
            } else {
                Toast.makeText(this, "pleast enter the user name ! ", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun readData(name: String) {
        database = FirebaseDatabase.getInstance().getReference("Guides")
        database.child(name).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("name").value
                val district = it.child("district").value
                val email = it.child("email").value
                val number = it.child("number").value
                val age = it.child("age").value
                val gender = it.child("gender").value

                Toast.makeText(this, "Guide Data Successfully get", Toast.LENGTH_SHORT).show()

                binding.searcguidename.text?.clear()
                binding.readname.text = name.toString()
                binding.readdistrict.text = district.toString()
                binding.reademail.text = email.toString()
                binding.readnumber.text = number.toString()
                binding.readage.text = age.toString()
                binding.readgender.text = gender.toString()
                // val Guide=Guide(name,district,email,number,age,gender)
            } else {
                Toast.makeText(this, "Guide not in the Database  get pleas enter the valid name", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }

    }
}
