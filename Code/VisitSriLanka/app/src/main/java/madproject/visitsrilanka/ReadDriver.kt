package madproject.visitsrilanka;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import madproject.visitsrilanka.databinding.ActivityReadDriverBinding

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ReadDriver : AppCompatActivity() {

    private lateinit var binding: ActivityReadDriverBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Read Drivers"
        binding.GetDetailsBtn.setOnClickListener {

            val driverName : String = binding.etusername.text.toString()
            if  (driverName.isNotEmpty()){

                readData(driverName)

            }else{

                Toast.makeText(this,"PLease enter the Driver name",Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun readData(driverName: String) {

        database = FirebaseDatabase.getInstance().getReference("DriverUsers")
        database.child(driverName).get().addOnSuccessListener {

            if (it.exists()){

                val driverName = it.child("driverName").value
                val email = it.child("email").value
                val phoneNumber = it.child("phoneNumber").value
                Toast.makeText(this,"Successfully Read",Toast.LENGTH_SHORT).show()
                binding.etusername.text.clear()
                binding.DnameTw.text = driverName.toString()
                binding.emailTw.text = email.toString()
                binding.PhoneTw.text = phoneNumber.toString()

            }else{

                Toast.makeText(this,"Driver Doesn't Exist",Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


        }



    }
}