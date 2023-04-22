package madproject.visitsrilanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        lateinit var database: DatabaseReference
        signInButton.setOnClickListener {

            var userPassword=signinUserPassword.text.toString()
            var userEmail=signinUserEmail.text.toString()

            var formattedUserEmail=userEmail.split(".").toTypedArray()

            //create database reference
            database=FirebaseDatabase.getInstance().getReference("AppUsers")
            database.child(formattedUserEmail[0]).get().addOnSuccessListener {

                if(it.exists()){

                    var appUserEmail=it.child("appUserEmail").value
                    var appUserPassword=it.child("appUserPassword").value
                    var appUserStatus=it.child("appUserStatus").value

                    //moving activities according to user status
                    moveActivityAccordingtoUserStatus(appUserStatus.toString(),appUserEmail.toString())


                    //reset input fields
                    resetEditTextsFieldsAfterSubmission()

                }//end if
                else{
                    Toast.makeText(this,"No user exists",Toast.LENGTH_SHORT).show()
                    Toast.makeText(this,"Just check your credentials again",Toast.LENGTH_SHORT).show()
                }//end else
            }.addOnFailureListener {
                Toast.makeText(this,"No user exists",Toast.LENGTH_SHORT).show()
                Toast.makeText(this,"Just check your credentials again",Toast.LENGTH_SHORT).show()
            }

        }//end method setOnClickListener
    }//end method onCreate()

    private fun moveActivityAccordingtoUserStatus(appUserStatus:String,appUserEmail:String){
        if(appUserStatus=="Tourist"){

            val moveToTouristMainActivity= Intent(this,TouristMainActivity::class.java)
            moveToTouristMainActivity.putExtra("touristEmail",appUserEmail.toString())
            Toast.makeText(this,"Not null ${appUserEmail.toString()}",Toast.LENGTH_SHORT).show()
            startActivity(moveToTouristMainActivity)
        }//end if
        else{

            val moveToOtherUsersMainActivity=Intent(this,OtherUsersMainActivity::class.java)
            startActivity(moveToOtherUsersMainActivity)
        }//end else
    }//end function moveActivityAccordingtoUserStatus

    private fun resetEditTextsFieldsAfterSubmission(){
        signinUserEmail.text.clear()
        signinUserPassword.text.clear()
    }//end function resetEditTextsFieldsAfterSubmission
}