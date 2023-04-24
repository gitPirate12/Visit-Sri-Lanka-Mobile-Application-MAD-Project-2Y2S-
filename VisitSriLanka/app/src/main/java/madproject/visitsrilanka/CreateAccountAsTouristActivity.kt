package madproject.visitsrilanka

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account_as_tourist.*
import java.io.ByteArrayOutputStream

class CreateAccountAsTouristActivity : AppCompatActivity() {

    private var touristImage:String?=null


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
                val tourist=Tourist(userTouristName,userTouristEmail,userTouristContactNumber,userTouristCountry,touristConfirmedPassword,touristImage)

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
                        resetInputFieldsAfterSubmission()
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

        uploadProfilePictureButton.setOnClickListener {

            var myFileIntent=Intent(Intent.ACTION_GET_CONTENT)
            myFileIntent.setType("image/*")
            ActivityResultLauncher.launch(myFileIntent)
        }//end method setOnClickListener

    }//end method onCreate


    private val ActivityResultLauncher=registerForActivityResult<Intent,ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result:ActivityResult->
        if(result.resultCode== RESULT_OK){
            val uri=result.data!!.data
            try{
                val inputStream=contentResolver.openInputStream(uri!!)
                val myBitmap=BitmapFactory.decodeStream(inputStream)
                val stream=ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
                val bytes=stream.toByteArray()
                touristImage=android.util.Base64.encodeToString(bytes,android.util.Base64.DEFAULT)
                uploadProfilePictureImageView.setImageBitmap(myBitmap)
                inputStream!!.close()
                Toast.makeText(this,"Image Selected",Toast.LENGTH_SHORT).show()

            }catch (ex:Exception){
                Toast.makeText(this,ex.message.toString(),Toast.LENGTH_SHORT).show()
            }//end try-catch block
        }//end if


    }//end val ActivityResultLauncher


    private fun resetInputFieldsAfterSubmission(){
        touristName.text.clear()
        touristEmail.text.clear()
        touristContactNumber.text.clear()
        touristCountry.text.clear()
        createdPassword.text.clear()
        confirmedPassword.text.clear()
        uploadProfilePictureImageView.setImageResource(R.drawable.baseline_account_box_24)
    }//end method resetInputFieldsAfterSubmission
}