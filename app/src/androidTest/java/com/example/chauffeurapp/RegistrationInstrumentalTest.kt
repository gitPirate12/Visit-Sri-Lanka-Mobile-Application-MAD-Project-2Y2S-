package com.example.chauffeurapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class RegistrationInstrumentalTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(DriverRegistration::class.java)

    @Test
    fun register_with_valid_data_is_successful() {
        onView(withId(R.id.dname))
            .perform(typeText("John Doe"), closeSoftKeyboard())
        onView(withId(R.id.dmail))
            .perform(typeText("johndoe@example.com"), closeSoftKeyboard())
        onView(withId(R.id.dphone))
            .perform(typeText("1234567890"), closeSoftKeyboard())
        onView(withId(R.id.dpass))
            .perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.dconpass))
            .perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.registerBtn))
            .perform(click())
        onView(withText("Successfully saved"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun register_with_empty_name_displays_error() {
        onView(withId(R.id.dname))
            .perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.dmail))
            .perform(typeText("johndoe@example.com"), closeSoftKeyboard())
        onView(withId(R.id.dphone))
            .perform(typeText("1234567890"), closeSoftKeyboard())
        onView(withId(R.id.dpass))
            .perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.dconpass))
            .perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.registerBtn))
            .perform(click())
        onView(withId(R.id.dname))
            .check(matches(hasErrorText("Driver name is required and must start with a capital letter and contain only letters and spaces")))
    }

    @Test
    fun register_with_invalid_name_displays_error() {
        onView(withId(R.id.dname))
            .perform(typeText("john doe"), closeSoftKeyboard())
        onView(withId(R.id.dmail))
            .perform(typeText("johndoe@example.com"), closeSoftKeyboard())
        onView(withId(R.id.dphone))
            .perform(typeText("1234567890"), closeSoftKeyboard())
        onView(withId(R.id.dpass))
            .perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.dconpass))
            .perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.registerBtn))
            .perform(click())
        onView(withId(R.id.dname))
            .check(matches(hasErrorText("Driver name is required and must start with a capital letter and contain only letters and spaces")))
    }
}