package com.example.chauffeurapp

import org.junit.Assert.*
import org.junit.Test



class DriverRegistrationTest {


    // Unit test for driver name validation
    @Test
    fun testDriverNameValidation() {
        // Valid driver name
        assertTrue("John Doe".matches(Regex("^[A-Z][a-zA-Z ]*[a-zA-Z]\$")))

        // Invalid driver name - starts with lowercase letter
        assertFalse("john Doe".matches(Regex("^[A-Z][a-zA-Z ]*[a-zA-Z]\$")))

        // Invalid driver name - contains special characters
        assertFalse("John Doe!".matches(Regex("^[A-Z][a-zA-Z ]*[a-zA-Z]\$")))
    }

    // Unit test for password validation
    @Test
    fun testPasswordValidation() {
        // Valid password - at least 6 characters
        assertTrue("123456".length >= 6)

        // Invalid password - less than 6 characters
        assertFalse("123".length >= 6)
    }
}
