package madproject.visitsrilanka

import android.net.Uri
import madproject.visitsrilanka.databinding.FragmentHotelFormBinding
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


class HotelFormTest {


    private lateinit var binding: FragmentHotelFormBinding



    @Test
    fun testFormSubmissionWithValidInput() {
        binding.hotelName.setText("Test Hotel")
        binding.hotelAddress.setText("123 Main Street")
        binding.hotelEmail.setText("test@example.com")
        binding.hotelPhone.setText("123-456-7890")
        binding.hotelPrice.setText("100")
        binding.hotelDistrict.setText("Colombo")

        val imageUri = Uri.parse("content://test-uri")

        binding.UploadsImageView.setImageURI(imageUri)

        binding.hotelRegButton.performClick()

        assertThat(binding.hotelName.text.toString(), equalTo(""))
        assertThat(binding.hotelAddress.text.toString(), equalTo(""))
        assertThat(binding.hotelEmail.text.toString(), equalTo(""))
        assertThat(binding.hotelPhone.text.toString(), equalTo(""))
        assertThat(binding.hotelPrice.text.toString(), equalTo(""))
        assertThat(binding.hotelDistrict.text.toString(), equalTo(""))
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

        assertThat(binding.hotelName.error.toString(), equalTo("Please Enter All Details"))
        assertThat(binding.hotelAddress.error.toString(), equalTo("Please Enter All Details"))
        assertThat(binding.hotelEmail.error.toString(), equalTo("Please enter a valid email address"))
        assertThat(binding.hotelPhone.error.toString(), equalTo("Please Enter All Details"))
        assertThat(binding.hotelPrice.error.toString(), equalTo("Please Enter All Details"))
        assertThat(binding.hotelDistrict.error.toString(), equalTo("Please Enter All Details"))
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

        assertThat(binding.UploadsImageView.drawable, nullValue())
    }


}
