package madproject.visitsrilanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


import com.google.firebase.database.*
import madproject.visitsrilanka.databinding.ActivityDeleteDriverBinding

class DeleteDriver : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteDriverBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Delete Driver"

        binding.driverDeleteBtn.setOnClickListener {
            val driverName = binding.deleteDriverTV.text.toString()
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
            binding.deleteDriverTV.setText(null)
            Toast.makeText(this, "Successfully deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }




}
