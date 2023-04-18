package com.example.testpaymentapp

import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

class InputFieldValidator @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {

    companion object {
        private const val EMAIL_PATTERN = "^\\S+@\\S+\\.\\S+$"
        private const val CARD_NUMBER_PATTERN =
            "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$"
        private const val CARD_DATE_PATTERN = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$"
        private const val CARD_CVC_PATTERN = "^[0-9]{3,4}$"
    }

    private val mainColor by lazy {
        resourceProvider.getColor(android.R.color.black)
    }

    private val errorColor by lazy {
        resourceProvider.getColor(android.R.color.holo_red_dark)
    }

    fun validateEmail(email: String, onValidationCompleted: (Boolean, Int) -> Unit) {
        if (isValidPattern(email, EMAIL_PATTERN)) {
            onValidationCompleted.invoke(
                true,
                mainColor,
            )
        } else {
            onValidationCompleted.invoke(
                false,
                errorColor,
            )
        }
    }

    fun validateNumber(number: String, onValidationCompleted: (Boolean, Int) -> Unit) {
        if (isValidPattern(number, CARD_NUMBER_PATTERN)) {
            onValidationCompleted.invoke(
                true,
                mainColor,
            )
        } else {
            onValidationCompleted.invoke(
                false,
                errorColor,
            )
        }
    }

    fun validateDate(date: String, onValidationCompleted: (Boolean, Int) -> Unit) {
        if (isValidPattern(date, CARD_DATE_PATTERN)) {
            onValidationCompleted.invoke(
                true,
                mainColor,
            )
        } else {
            onValidationCompleted.invoke(
                false,
                errorColor,
            )
        }
    }

    fun validateCVC(cvc: String, onValidationCompleted: (Boolean, Int) -> Unit) {
        if (isValidPattern(cvc, CARD_CVC_PATTERN)) {
            onValidationCompleted.invoke(
                true,
                mainColor,
            )
        } else {
            onValidationCompleted.invoke(
                false,
                errorColor,
            )
        }
    }

    private fun isValidPattern(text: String, regex: String): Boolean {
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(text)
        return matcher.find()
    }
}
