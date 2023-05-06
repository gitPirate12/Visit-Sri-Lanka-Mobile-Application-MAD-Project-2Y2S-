package madproject.visitsrilanka

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HotelDetails:AppCompatActivity() {

    private lateinit var hotelListNametv:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hotel_item)
        initView()
        setValuesToView()
    }

    private fun initView() {
        hotelListNametv=findViewById(R.id.hotelListNametv)

    }
    private fun setValuesToView(){
        hotelListNametv.text=intent.getStringExtra("hotelName")

    }


}