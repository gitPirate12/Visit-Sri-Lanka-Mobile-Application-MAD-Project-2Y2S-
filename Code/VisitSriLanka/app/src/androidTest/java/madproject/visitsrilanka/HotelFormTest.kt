package madproject.visitsrilanka

import android.net.Uri
import android.view.LayoutInflater
import androidx.test.core.app.ApplicationProvider
import madproject.visitsrilanka.databinding.FragmentHotelFormBinding
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test


internal class HotelFormTest{ private lateinit var binding: FragmentHotelFormBinding
    @Before
    fun set(){

        binding= FragmentHotelFormBinding.inflate(LayoutInflater.from(ApplicationProvider.getApplicationContext()))
    }



    @Test
    fun testFormSubmissionWithValidInput() {
        binding.hotelName.setText("Test Hotel")
        binding.hotelAddress.setText("123 Main Street")
        binding.hotelEmail.setText("test@example.com")
        binding.hotelPhone.setText("123457890")
        binding.hotelPrice.setText("100")
        binding.hotelDistrict.setText("Colombo")

        val imageUri = Uri.parse("content://test-uri")

        binding.UploadsImageView.setImageURI(imageUri)

        binding.hotelRegButton.performClick()

        MatcherAssert.assertThat(binding.hotelName.text.toString(), CoreMatchers.equalTo(""))
        MatcherAssert.assertThat(binding.hotelAddress.text.toString(), CoreMatchers.equalTo(""))
        MatcherAssert.assertThat(binding.hotelEmail.text.toString(), CoreMatchers.equalTo(""))
        MatcherAssert.assertThat(binding.hotelPhone.text.toString(), CoreMatchers.equalTo(""))
        MatcherAssert.assertThat(binding.hotelPrice.text.toString(), CoreMatchers.equalTo(""))
        MatcherAssert.assertThat(binding.hotelDistrict.text.toString(), CoreMatchers.equalTo(""))
    }

    @Test
    fun testFormSubmissionWithInvalidInput() {
        binding.hotelName.setText("")
        binding.hotelAddress.setText("")
        binding.hotelEmail.setText("invalid-email")
        binding.hotelPhone.setText("")
        binding.hotelPrice.setText("")
        binding.hotelDistrict.setText("")

        binding.hotelRegButton.performClick()

        MatcherAssert.assertThat(
            binding.hotelName.error.toString(),
            CoreMatchers.equalTo("Please Enter All Details")
        )
        MatcherAssert.assertThat(
            binding.hotelAddress.error.toString(),
            CoreMatchers.equalTo("Please Enter All Details")
        )
        MatcherAssert.assertThat(
            binding.hotelEmail.error.toString(),
            CoreMatchers.equalTo("Please enter a valid email address")
        )
        MatcherAssert.assertThat(
            binding.hotelPhone.error.toString(),
            CoreMatchers.equalTo("Please Enter All Details")
        )
        MatcherAssert.assertThat(
            binding.hotelPrice.error.toString(),
            CoreMatchers.equalTo("Please Enter All Details")
        )
        MatcherAssert.assertThat(
            binding.hotelDistrict.error.toString(),
            CoreMatchers.equalTo("Please Enter All Details")
        )
    }

    @Test
    fun testResetInputFieldsAfterSubmission() {
        binding.hotelName.setText("Test Hotel")
        binding.hotelAddress.setText("123 Main Street")
        binding.hotelEmail.setText("test@example.com")
        binding.hotelPhone.setText("123-456-7890")
        binding.hotelPrice.setText("100")
        binding.hotelDistrict.setText("Colombo")

        val imageUri = Uri.parse("content://test-uri")

        binding.UploadsImageView.setImageURI(imageUri)

        binding.hotelRegButton.performClick()

        MatcherAssert.assertThat(binding.UploadsImageView.drawable, CoreMatchers.nullValue())
    }


}