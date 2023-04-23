package com.example.testpaymentapp.validation

import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

interface InputFieldValidator {

    fun validate(input: String, type: ValidationType): ValidationResult
}


class InputFieldValidatorImpl @Inject constructor() : InputFieldValidator {

    override fun validate(input: String, type: ValidationType): ValidationResult =
        if (isValidPattern(input, type.regex)) ValidationResult.Success else ValidationResult.Fail

    private fun isValidPattern(text: String, regex: String): Boolean {
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(text)
        return matcher.find()
    }
}
