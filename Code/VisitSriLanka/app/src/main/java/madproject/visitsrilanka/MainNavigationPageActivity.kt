package madproject.visitsrilanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_navigation_page.*

class MainNavigationPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation_page)

        createAccountAsTouristButton.setOnClickListener {

            val moveToCreateAccountAsTouristActivity= Intent(this,CreateAccountAsTouristActivity::class.java)
            startActivity(moveToCreateAccountAsTouristActivity)

        }//end method setOnClickListener

        signInButton.setOnClickListener {

            val moveToSignInActivity=Intent(this,SignInActivity::class.java)
            startActivity(moveToSignInActivity)

        }//end method setOnClickListener

        hotelRegistrationButton.setOnClickListener{
            val hotelRegNav=Intent(this,Hotel_reg_nav::class.java)
            startActivity(hotelRegNav)
        }

        tourDriverRegistrationButton.setOnClickListener{
            val driverMenuNav=Intent(this,DriverMainMenu::class.java)
            startActivity(driverMenuNav)
        }


        tourGuideRegistrationButton.setOnClickListener{
            val guidemain=Intent(this,guidemainpage::class.java)
            startActivity(guidemain)
        }
    }//end method onCreate
}