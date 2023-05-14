package madproject.visitsrilanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import madproject.visitsrilanka.databinding.ActivityUpdateguideBinding

class updateguide : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateguideBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateguideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener{
            val name=binding.uname.text.toString()
            val email=binding.uemail.text.toString()
            val district=binding.udistrict.text.toString()
            val number=binding.unumber.text.toString()
            val age=binding.uage.text.toString()
            val gender=binding.ugender.text.toString()

            if(name.isEmpty()){
                binding.uname.error="Guide name is required and must inclued letters only"
                return@setOnClickListener
            }

            if(district.isEmpty()){
                binding.udistrict.error="District name is Required"
                return@setOnClickListener
            }
            if(email.isEmpty()){
                binding.uemail.error="Email name is Required"
                return@setOnClickListener
            }
            if(number.isEmpty()|| number.length<10){
                binding.unumber.error="Number name is Required and It must include 10 Digits"
                return@setOnClickListener
            }
            if(age.isEmpty()|| age.length<2){
                binding.uage.error="Gge name is Required it must be with in two Digits"
                return@setOnClickListener
            }
            if(gender.isEmpty()){
                binding.ugender.error="Gender name is Required"
                return@setOnClickListener
            }

            updateData(name,email,district,number,age, gender)

        }

    }
    private fun updateData(name:String,
                           email:String,
                           district:String,
                           number:String,
                           age:String,
                           gender:String,
    ){
        database= FirebaseDatabase.getInstance().getReference("Guides")
        val Guide = mapOf<String,String>(
            "name" to name,
            "email" to email,
            "district" to district,
            "number" to number,
            "age" to age,
            "gender" to gender,
        )
        database.child(name).updateChildren(Guide).addOnSuccessListener{
            binding.uname.text.clear()
            binding.uemail.text.clear()
            binding.udistrict.text.clear()
            binding.unumber.text.clear()
            binding.uage.text.clear()
            binding.ugender.text.clear()

            Toast.makeText(this,"Successfully Guide Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed to update", Toast.LENGTH_SHORT).show()
        }

    }
}
