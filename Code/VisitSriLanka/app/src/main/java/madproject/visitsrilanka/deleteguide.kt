package madproject.visitsrilanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import madproject.visitsrilanka.databinding.ActivityDeleteguideBinding

//activity_deleteguide

class deleteguide : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteguideBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDeleteguideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deletebtn.setOnClickListener{
            var dname=binding.dname.text.toString()

            if (dname.isNotEmpty())
                deleteData(dname)
            else
                Toast.makeText(this, "please enter the GuideName", Toast.LENGTH_SHORT).show()

        }
    }
    private fun deleteData(dname: String){
        database= FirebaseDatabase.getInstance().getReference("Guides")
        database.child(dname).removeValue().addOnSuccessListener {

            binding.dname.text.clear()
            Toast.makeText(this,"Guide Deleted", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Deleted Failed", Toast.LENGTH_SHORT).show()
        }

    }
}