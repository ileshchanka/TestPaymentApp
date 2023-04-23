package com.example.testpaymentapp

import com.example.testpaymentapp.validation.InputFieldValidatorImpl
import com.example.testpaymentapp.validation.ValidationResult
import com.example.testpaymentapp.validation.ValidationType
import org.junit.Assert.assertEquals
import org.junit.Test

class InputFieldValidatorTest {

    @Test
    fun testIsValidEmail() {
        val validator = InputFieldValidatorImpl()
        assertEquals(
            validator.validate("example@gmail.com", ValidationType.EMAIL),
            ValidationResult.Success
        )
        assertEquals(
            validator.validate("example.com", ValidationType.EMAIL),
            ValidationResult.Fail
        )
    }
}
