package madproject.visitsrilanka


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import madproject.visitsrilanka.databinding.FragmentHotelFormBinding


class HotelForm : Fragment() {

    lateinit var hotelName: String
    lateinit var hotelAddress: String
    lateinit var hotelEmail: String
    lateinit var hotelPhone: String
    lateinit var hotelPrice: String
    lateinit var hotelDistrict: String
    lateinit var hotelImage: String


    var uri:Uri?=null


    lateinit var dataBase: DatabaseReference
    private lateinit var binding: FragmentHotelFormBinding
    private lateinit var storageReference: StorageReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        //
        // Inflate the layout for this fragment
        binding = FragmentHotelFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storageReference = FirebaseStorage.getInstance().reference

        var myBundle = arguments
        hotelName = myBundle?.getString("hotelName").toString()


        binding.uploadSomePicturesButton.setOnClickListener {
            val myFileIntent = Intent(Intent.ACTION_GET_CONTENT)
            myFileIntent.setType("image/*")
            resultLauncher.launch(myFileIntent)
        }

        binding.hotelRegButton.setOnClickListener {



            hotelName = binding.hotelName.text.toString()
            hotelAddress = binding.hotelAddress.text.toString()
            hotelEmail = binding.hotelEmail.text.toString()
            hotelPhone = binding.hotelPhone.text.toString()
            hotelPrice = binding.hotelPrice.text.toString()
            hotelDistrict = binding.hotelDistrict.text.toString()


            //validating hotel reg inputs
            if (TextUtils.isEmpty(hotelName.toString())) {
                Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

            } else
                if (TextUtils.isEmpty(hotelAddress.toString())) {
                    Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

                } else if (TextUtils.isEmpty(hotelEmail.toString())) {
                    Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

                } else if (TextUtils.isEmpty(hotelPhone.toString())) {
                    Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

                } else if (TextUtils.isEmpty(hotelPrice.toString())) {
                    Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

                } else if (TextUtils.isEmpty(hotelDistrict.toString())) {
                    android.widget.Toast.makeText(
                        activity,
                        "Please Enter All Details",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()

                }
            if (!isValidEmail(hotelEmail)) {
                Toast.makeText(activity, "Please enter a valid email address", Toast.LENGTH_SHORT).show()

            }else
                {

                    saveHotelInDatabase(
                        hotelName,
                        hotelAddress,
                        hotelEmail,
                        hotelPhone,
                        hotelPrice,
                        hotelDistrict,
                        hotelImage
                    )
                }
        }

    }
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun saveHotelInDatabase(
        hotelName: String,
        hotelAddress: String,
        hotelEmail: String,
        hotelPhone: String,
        hotelPrice: String,
        hotelDistrict: String,
        hotelImage: String
    ) {

        val hotelData = HotelData(
            hotelName,
            hotelAddress,
            hotelEmail,
            hotelPhone,
            hotelPrice,
            hotelDistrict,
            hotelImage
        )
        dataBase = FirebaseDatabase.getInstance().getReference("Hotel")
        var childValue = hotelName
        dataBase.child(childValue).setValue(hotelData).addOnSuccessListener {
            Toast.makeText(activity, "complete", Toast.LENGTH_SHORT).show()
            resetInputFieldsAfterSubmission()
        }
    }


    private fun resetInputFieldsAfterSubmission() {

        binding.hotelName.text.clear()
        binding.hotelAddress.text.clear()
        binding.hotelEmail.text.clear()
        binding.hotelPhone.text.clear()
        binding.hotelPrice.text.clear()
        binding.hotelDistrict.text.clear()

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //how to access drawable folder from fragment
        var myDrawable = activity?.resources?.getDrawable(R.drawable.baseline_upload_file_24)

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            if (data != null && data.data != null) {
                val imageUri = data.data
                if (imageUri != null) {
                    val imageRef = storageReference.child("hotelImages/${hotelName}_${System.currentTimeMillis()}.jpg")
                    val uploadTask = imageRef.putFile(imageUri)
                    uploadTask.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            imageRef.downloadUrl.addOnSuccessListener { uri ->
                                hotelImage = uri.toString()
                                binding.UploadsImageView.setImageURI(uri)
                            }.addOnFailureListener { exception ->
                                Toast.makeText(requireContext(), "Failed to upload image: ${exception.message}", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(requireContext(), "Failed to upload image: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to get image URI", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Failed to get image data", Toast.LENGTH_SHORT).show()
            }

        }
    }



}


