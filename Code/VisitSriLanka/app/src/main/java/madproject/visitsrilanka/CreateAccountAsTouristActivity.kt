package madproject.visitsrilanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account_as_tourist.*

class CreateAccountAsTouristActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_as_tourist)

        lateinit var dataBase:DatabaseReference
        lateinit var dataBase2:DatabaseReference


        createAccountButton.setOnClickListener {

            //check whether confirmed password and created password are same
            val touristCreatedPassword=createdPassword.text.toString()
            val touristConfirmedPassword=confirmedPassword.text.toString()

            if(touristCreatedPassword == touristConfirmedPassword){

                //get Values From Input Fields
                val userTouristName=touristName.text.toString()
                val userTouristEmail=touristEmail.text.toString()
                val userTouristContactNumber=touristContactNumber.text.toString()
                val userTouristCountry=touristCountry.text.toString()

                //formatting 'userTouristEmail' to 'formattedEmail'
                //this 'formattedEmail' use as primary key
                val formattedEmail=userTouristEmail.split(".").toTypedArray()

                //create Tourist object
                val tourist=Tourist(userTouristName,userTouristEmail,userTouristContactNumber,userTouristCountry,touristConfirmedPassword)

                //create AppUsers object
                val appUser=AppUsers(userTouristEmail,touristConfirmedPassword,"Tourist")

                //pass data to 'Tourist' in 'Visit Sri Lanka'
                dataBase=FirebaseDatabase.getInstance().getReference("Tourist")
                dataBase.child(formattedEmail[0]).setValue(tourist).addOnSuccessListener {
                    //this code block will execute if data successfully submitted

                    //pass data  to 'AppUsers' in 'Visit Sri Lanka'
                    dataBase2=FirebaseDatabase.getInstance().getReference("AppUsers")
                    dataBase2.child(formattedEmail[0]).setValue(appUser).addOnSuccessListener {
                        Toast.makeText(this,"You Created Tourist Account Successfully",Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this,"Tourist Account Creation is Unsuccessful",Toast.LENGTH_SHORT).show()
                    }

                }.addOnFailureListener {
                    //this code block will execute if data not successfully submitted
                    Toast.makeText(this,"Tourist Account Creation is Unsuccessful",Toast.LENGTH_SHORT).show()
                }

            }//end if
            else{
                Toast.makeText(this,"Please Check Password Again",Toast.LENGTH_SHORT).show()
            }//else




        }//end method setOnClickListener
    }//end method onCreate
}