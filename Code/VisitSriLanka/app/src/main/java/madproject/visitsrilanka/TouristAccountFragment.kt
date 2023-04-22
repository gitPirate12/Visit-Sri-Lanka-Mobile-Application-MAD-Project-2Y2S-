package madproject.visitsrilanka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_tourist_account.*


class TouristAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //get values passed by bundle
        var bundle=arguments
        var touristEmail=bundle?.getString("touristEmail")
        //var touristEmail= this.arguments?.getString("touristEmail")

        if(touristEmail!=null){

            var touristEmailTextView= view?.findViewById<TextView>(R.id.touristEmailTextView)
            if (touristEmailTextView != null) {
                touristEmailTextView.text=touristEmail
            }//end if
            else{
                Toast.makeText(activity,"cannot access view",Toast.LENGTH_SHORT).show()
            }//end else


           // touristEmailTextView.text = touristEmail

            Toast.makeText(activity,"touristEmail($touristEmail) is exists",Toast.LENGTH_SHORT).show()
        }//end if
        else{

            Toast.makeText(activity,"touristEmail is not Exists",Toast.LENGTH_SHORT).show()
        }//end else

        //touristEmailTextView.text = touristEmail


        return inflater.inflate(R.layout.fragment_tourist_account, container, false)
    }


}