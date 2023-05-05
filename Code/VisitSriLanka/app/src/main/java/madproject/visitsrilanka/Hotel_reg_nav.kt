package madproject.visitsrilanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hotel_reg_nav.*
import kotlinx.android.synthetic.main.activity_main_navigation_page.*

class Hotel_reg_nav : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_reg_nav)


        hotelRegistrationFormButton.setOnClickListener{
            val hotelRegNav= Intent(this,HotelRegistration::class.java)
            startActivity(hotelRegNav)
        }
        hotelListButton.setOnClickListener{
            val hotelRegNav= Intent(this,hotelListPage::class.java)
            startActivity(hotelRegNav)
        }
    }
}