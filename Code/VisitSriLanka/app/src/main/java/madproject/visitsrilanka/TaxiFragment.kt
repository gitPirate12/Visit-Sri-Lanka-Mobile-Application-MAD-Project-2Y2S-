package madproject.visitsrilanka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.NumberFormat

class TaxiFragment : Fragment() {

    private lateinit var vehicleClassSpinner: Spinner
    private lateinit var hoursNoET: EditText
    private lateinit var calculatedChargeTV: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_taxi, container, false)

        vehicleClassSpinner = view.findViewById(R.id.vehicle_class_spinner)

        val vehicleClasses = arrayOf("Luxury", "Semi-Luxury", "Economy")
        val vehicleAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, vehicleClasses)
        vehicleClassSpinner.adapter = vehicleAdapter

        hoursNoET = view.findViewById(R.id.hoursNo_ET)
        calculatedChargeTV = view.findViewById(R.id.CalcualtedChargeTextView)
        val calcDriverChargeButton: Button = view.findViewById(R.id.calcdriverchargeButton)
        calcDriverChargeButton.setOnClickListener {
            val hours = hoursNoET.text.toString().toIntOrNull() ?: 0
            val hourlyCharge = when (vehicleClassSpinner.selectedItemPosition) {
                0 -> 800
                1 -> 600
                else -> 400
            }
            val totalCharge = hours * hourlyCharge
            val formatter = NumberFormat.getCurrencyInstance()
            calculatedChargeTV.text = formatter.format(totalCharge)
        }

        val confirmBookingButton: Button = view.findViewById(R.id.confirm_booking_button)
        confirmBookingButton.setOnClickListener {
            // Show toast message
            Toast.makeText(requireContext(), "Booking is successful", Toast.LENGTH_SHORT).show()

            // Clear fields
            vehicleClassSpinner.setSelection(0)
            hoursNoET.setText("")
            calculatedChargeTV.text = "$0.00"
        }

        return view
    }
}
