package madproject.visitsrilanka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import madproject.visitsrilanka.DriverUser
import java.text.NumberFormat

class TaxiFragment : Fragment() {

    private lateinit var driverSpinner: Spinner
    private lateinit var driverRef: DatabaseReference
    private var driverList = mutableListOf<String>()
    private lateinit var hoursNoET: EditText
    private lateinit var calculatedChargeTV: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_taxi, container, false)

        driverSpinner = view.findViewById(R.id.driver_spinner)
        driverRef = FirebaseDatabase.getInstance().getReference("DriverUser")

        val driverAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, driverList)
        driverSpinner.adapter = driverAdapter

        driverRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                driverList.clear()
                for (ds in snapshot.children) {
                    val driver = ds.getValue(DriverUser::class.java)
                    driver?.let {
                        driverList.add(driver.driverName!!)
                    }
                }
                driverAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })

        hoursNoET = view.findViewById(R.id.hoursNo_ET)
        calculatedChargeTV = view.findViewById(R.id.CalcualtedChargeTextView)
        val calcDriverChargeButton: Button = view.findViewById(R.id.calcdriverchargeButton)
        calcDriverChargeButton.setOnClickListener {
            val hours = hoursNoET.text.toString().toIntOrNull() ?: 0
            val hourlyCharge = 500
            val totalCharge = hours * hourlyCharge
            val formatter = NumberFormat.getCurrencyInstance()
            calculatedChargeTV.text = formatter.format(totalCharge)
        }

        return view
    }
}
