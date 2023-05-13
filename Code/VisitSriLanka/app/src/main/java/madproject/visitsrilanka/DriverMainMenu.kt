package madproject.visitsrilanka


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import madproject.visitsrilanka.R


class DriverMainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_mainmenu)
        title = "Main Menu"

        val deleteBtn = findViewById<Button>(R.id.DeleteDriverBtn)
        deleteBtn.setOnClickListener {
            val intent = Intent(this, DeleteDriver::class.java)
            startActivity(intent)
        }

        val editProfileBtn = findViewById<Button>(R.id.editProfileBtn)
        editProfileBtn.setOnClickListener {
            val intent = Intent(this, DriverEditProfile::class.java)
            startActivity(intent)
        }

        val registerBtn = findViewById<Button>(R.id.MainmenuRegisterBtn)
        registerBtn.setOnClickListener {
            val intent = Intent(this, DriverRegistration::class.java)
            startActivity(intent)
        }

        val bookNowBtn = findViewById<Button>(R.id.findDriverBtn)
        bookNowBtn.setOnClickListener {
            val intent = Intent(this, ReadDriver::class.java)
            startActivity(intent)
        }
    }
}
