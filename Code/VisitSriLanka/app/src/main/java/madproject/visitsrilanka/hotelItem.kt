package madproject.visitsrilanka

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.drawToBitmap
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase




class hotelItem : AppCompatActivity() {


    private lateinit var hotelListNametv: TextView
    private lateinit var hotelListImagetv: ImageView
    private lateinit var hotelListAddresstv: TextView
    private lateinit var hotelListEmailtv: TextView
    private lateinit var hotelListPhonetv: TextView
    private lateinit var hotelListPricetv: TextView
    private lateinit var hotelListDistricttv: TextView
    private lateinit var btnUpdate:Button
    private lateinit var btnDelete:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_item)

        initView()
        setValuesToView()


        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("hotelName").toString()

            )
        }
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("hotelName").toString()

            )
        }

    }

    //delete selected records
    private fun deleteRecord(
        hotelName: String
    ){
        val dbRef= FirebaseDatabase.getInstance().getReference("Hotel").child(hotelName)
        val mTask =dbRef.removeValue()
        mTask.addOnSuccessListener {
            Toast.makeText(applicationContext,"Data Deleted",Toast.LENGTH_LONG).show()
            val intent= Intent(this,hotelListPage::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(applicationContext,"Delete Err ${error.message}",Toast.LENGTH_LONG).show()

        }
    }


    private fun initView() {
        hotelListNametv=findViewById(R.id.hotelListNametv)
        hotelListAddresstv=findViewById(R.id.hotelListAddresstv)
        hotelListEmailtv = findViewById(R.id.hotelListEmailtv)
        hotelListPhonetv=findViewById(R.id.hotelListPhonetv)
        hotelListPricetv=findViewById(R.id.hotelListPricetv)
        hotelListDistricttv = findViewById(R.id.hotelListDistricttv)
        hotelListImagetv=findViewById(R.id.hotelListImagetv)
        btnUpdate=findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)




    }
    private fun setValuesToView(){
        hotelListNametv.text=intent.getStringExtra("hotelName")
        hotelListAddresstv.text=intent.getStringExtra("hotelAddress")
        hotelListEmailtv.text=intent.getStringExtra("hotelEmail")
        hotelListPhonetv.text=intent.getStringExtra("hotelPhone")
        hotelListPricetv.text=intent.getStringExtra("hotelPrice")
        hotelListDistricttv.text=intent.getStringExtra("hotelDistrict")
        val hotelImageUrl = intent.getStringExtra("hotelImage")
        Glide.with(this)
            .load(hotelImageUrl)
            .into(hotelListImagetv)

    }

    private fun openUpdateDialog(
        hotelName:String
    ){
        val mDialog= AlertDialog.Builder(this)
        val inflater=layoutInflater
        val mDialogView= inflater.inflate(R.layout.activity_update_dialog,null)
        mDialog.setView(mDialogView)

        val hotelName=mDialogView.findViewById<EditText>(R.id.hotelName)
        val hotelAddress=mDialogView.findViewById<EditText>(R.id.hotelAddress)
        val hotelEmail=mDialogView.findViewById<EditText>(R.id.hotelEmail)
        val hotelPhone=mDialogView.findViewById<EditText>(R.id.hotelPhone)
        val hotelPrice=mDialogView.findViewById<EditText>(R.id.hotelPrice)
        val hotelDistrict=mDialogView.findViewById<EditText>(R.id.hotelDistrict)
        val UploadsImageView=mDialogView.findViewById<ImageView>(R.id.UploadsImageView)
        val btnSelectImage = mDialogView.findViewById<Button>(R.id.btnSelectImage)

        val btnUpdateData=mDialogView.findViewById<Button>(R.id.btnUpdateData)



        hotelName.setText(intent.getStringExtra("hotelName").toString())
        hotelAddress.setText(intent.getStringExtra("hotelAddress").toString())
        hotelEmail.setText(intent.getStringExtra("hotelEmail").toString())
        hotelPhone.setText(intent.getStringExtra("hotelPhone").toString())
        hotelPrice.setText(intent.getStringExtra("hotelPrice").toString())
        hotelDistrict.setText(intent.getStringExtra("hotelDistrict").toString())
        val hotelImageUrl = intent.getStringExtra("hotelImage")
        Glide.with(this)
            .load(hotelImageUrl)
            .into(UploadsImageView)


        val alertDialog=mDialog.create()
        alertDialog.show()
        btnSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }


        btnUpdateData.setOnClickListener {
            updateHotelData(
                hotelName.text.toString(),
                hotelAddress.text.toString(),
                hotelEmail.text.toString(),
                hotelPhone.text.toString(),
                hotelPrice.text.toString(),
                hotelDistrict.text.toString(),
                hotelImageUrl.toString()
            )
            Toast.makeText(applicationContext,"Data Updated",Toast.LENGTH_LONG).show()

            hotelListNametv.text=hotelName.text.toString()
            hotelListAddresstv.text=hotelAddress.text.toString()
            hotelListEmailtv.text= hotelEmail.text.toString()
            hotelListPhonetv.text=hotelPhone.text.toString()
            hotelListPricetv.text=hotelPrice.text.toString()
            hotelListDistricttv.text=hotelDistrict.text.toString()

            alertDialog.dismiss()
        }


    }

    private fun updateHotelData(
        hotelName: String,
        hotelAddress: String,
        hotelEmail:String,
        hotelPhone: String,
        hotelPrice:String,
        hotelDistrict:String,
        hotelImage:String
    ){

        val dbRef= FirebaseDatabase.getInstance().getReference("Hotel").child(hotelName)
        val hoteldata=HotelData(hotelName,hotelAddress,hotelEmail,hotelPhone,hotelPrice,hotelDistrict,hotelImage)
        dbRef.setValue(hoteldata)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            val imageUri = data.data
            hotelListImagetv.setImageURI(imageUri)
        }




    }

}
