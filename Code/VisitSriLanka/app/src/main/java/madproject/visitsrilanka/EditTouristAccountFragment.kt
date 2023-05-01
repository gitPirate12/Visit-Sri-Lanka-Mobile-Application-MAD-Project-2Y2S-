package madproject.visitsrilanka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import madproject.visitsrilanka.databinding.FragmentEditTouristAccountBinding
import madproject.visitsrilanka.databinding.FragmentTouristAccountBinding

class EditTouristAccountFragment : Fragment() {

    //fragment binding
    private lateinit var binding:FragmentEditTouristAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //fragment binding
        binding= FragmentEditTouristAccountBinding.inflate(layoutInflater)
        return binding.root
    }//end method onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get values sending with bundle
        var bundle=arguments
        var touristName= bundle?.getString("touristName")
        var touristEmail= bundle?.getString("touristEmail")
        var touristCountry= bundle?.getString("touristCountry")
        var touristContactNumber= bundle?.getString("touristContactNumber")

        //set values to views
        binding.touristEmail.setText(touristEmail)
        binding.editTouristName.setText(touristName)
        binding.editTouristCountry.setText(touristCountry)
        binding.editTouristContactNumber.setText(touristContactNumber)
        
    }//end method onViewCreated


}