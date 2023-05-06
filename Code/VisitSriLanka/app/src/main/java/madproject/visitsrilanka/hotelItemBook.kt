package madproject.visitsrilanka

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase

class hotelItemBook : AppCompatActivity() {
    private lateinit var hotelListNametv: TextView
    private lateinit var hotelListImagetv: ImageView
    private lateinit var hotelListAddresstv: TextView
    private lateinit var hotelListEmailtv: TextView
    private lateinit var hotelListPhonetv: TextView
    private lateinit var hotelListPricetv: TextView
    private lateinit var hotelListDistricttv: TextView
    private lateinit var btnBook: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_item_book)

        initView()
        setValuesToView()


        btnBook.setOnClickListener {
            openBookDialog(
                intent.getStringExtra("hotelName").toString()

            )
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
        btnBook=findViewById(R.id.btnBook)





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
    private fun createImageBitmap(imageData: String?): Bitmap {
        val bytes = android.util.Base64.decode(imageData, android.util.Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun openBookDialog(
        hotelName:String
    ){
        val mDialog= AlertDialog.Builder(this)
        val inflater=layoutInflater
        val mDialogView= inflater.inflate(R.layout.activity_book_dialog,null)
        mDialog.setView(mDialogView)

        val hotelName=mDialogView.findViewById<TextView>(R.id.hotelName)
        val hotelAddress=mDialogView.findViewById<TextView>(R.id.hotelAddress)
        val CustomerName=mDialogView.findViewById<EditText>(R.id.CustomerName)
        val hotelPhone=mDialogView.findViewById<TextView>(R.id.hotelPhone)
        val hotelPrice=mDialogView.findViewById<TextView>(R.id.hotelPrice)
        val BookDays=mDialogView.findViewById<EditText>(R.id.BookDays)


        val btnUpdateData=mDialogView.findViewById<Button>(R.id.btnUpdateData)



        hotelName.setText(intent.getStringExtra("hotelName").toString())
        hotelAddress.setText(intent.getStringExtra("hotelAddress").toString())

        hotelPhone.setText(intent.getStringExtra("hotelPhone").toString())
        hotelPrice.setText(intent.getStringExtra("hotelPrice").toString())







        val alertDialog=mDialog.create()
        alertDialog.show()



        btnUpdateData.setOnClickListener {
            Resrevation(
                hotelName.text.toString(),
                hotelAddress.text.toString(),
                hotelPhone.text.toString(),
                hotelPrice.text.toString(),
                CustomerName.text.toString(),
                BookDays.text.toString(),

            )
            Toast.makeText(applicationContext,"Reservation Complete", Toast.LENGTH_LONG).show()



            alertDialog.dismiss()
        }


    }

    private fun Resrevation(
        hotelName: String,
        hotelAddress: String,
        hotelPhone: String,
        hotelPrice:String,
        CustomerName:String,
       BookDays:String,

    ){

        val dbRef= FirebaseDatabase.getInstance().getReference("Hotel_Book").child(CustomerName)
        val hoteldata=hotelBookData(hotelName,hotelAddress,hotelPhone,hotelPrice,CustomerName,BookDays)
        dbRef.setValue(hoteldata)

    }







}