package madproject.visitsrilanka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_tourist_account.*
import madproject.visitsrilanka.databinding.FragmentTouristAccountBinding


class TouristAccountFragment : Fragment() {

    private lateinit var binding: FragmentTouristAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        //fragment binding
        binding=FragmentTouristAccountBinding.inflate(layoutInflater)
        return binding.root
    }//end method onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get values passed by bundle
        var bundle=arguments
        var touristEmail=bundle?.getString("touristEmail")
        //var touristEmail= this.arguments?.getString("touristEmail")

        if(touristEmail!=null){
            
            Toast.makeText(activity,"touristEmail $touristEmail recieved by fragment",Toast.LENGTH_SHORT).show()
            binding.touristEmailTextView.text=touristEmail
        }//end if
        else{
            Toast.makeText(activity,"touristEmail is not Exists",Toast.LENGTH_SHORT).show()
        }//end else


    }//end method onViewCreated


}