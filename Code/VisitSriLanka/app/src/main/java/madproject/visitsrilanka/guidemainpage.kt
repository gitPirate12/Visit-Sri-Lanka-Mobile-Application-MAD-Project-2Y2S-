package madproject.visitsrilanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class guidemainpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guidemainpage)
        title="Guide menu"


        val seeButton = findViewById<Button>(R.id.seeguidebutton)
        seeButton.setOnClickListener {
            val intent = Intent(this,guidedetail::class.java)
            startActivity(intent)
        }

        val myButton = findViewById<Button>(R.id.addguidebtn)
        myButton.setOnClickListener {
            val intent = Intent(this,guideregister::class.java)
            startActivity(intent)
        }


        val updateButton = findViewById<Button>(R.id.updateguidebutton)
        updateButton.setOnClickListener {
            val intent = Intent(this,updateguide::class.java)
            startActivity(intent)
        }

        val deleteButton = findViewById<Button>(R.id.deletebtn)
        deleteButton.setOnClickListener {
            val intent = Intent(this,deleteguide::class.java)
            startActivity(intent)
        }

        val readButton = findViewById<Button>(R.id.findguidebtn)
        readButton.setOnClickListener {
            val intent = Intent(this,readguide::class.java)
            startActivity(intent)
        }


    }
}