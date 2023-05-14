package madproject.visitsrilanka

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HotelFormTest {

    @Test
    fun testIsValidEmail() {
        val hotelForm = HotelForm()
        assertTrue(hotelForm.isValidEmail("example@example.com"))
    }
}