package com.example.testpaymentapp

import androidx.annotation.ColorInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel(
    private val validator: InputFieldValidator,
    resourceProvider: ResourceProvider,
) : ViewModel() {

    private val mainColor = resourceProvider.getColor(R.color.black)

    val viewState = MutableLiveData(
        ViewState(
            emailColor = mainColor,
            numberColor = mainColor,
            dateColor = mainColor,
            cvcColor = mainColor,
        )
    )

    data class ViewState(
        val isValidEmail: Boolean = false,
        val isValidNumber: Boolean = false,
        val isValidDate: Boolean = false,
        val isValidCVC: Boolean = false,
        @ColorInt val emailColor: Int = 0,
        @ColorInt val numberColor: Int = 0,
        @ColorInt val dateColor: Int = 0,
        @ColorInt val cvcColor: Int = 0,
        val isPayButtonEnabled: Boolean = false,
    )

    fun emailTextChanged(email: String) {
        validator.validateEmail(email) { isValidEmail, _ ->
            viewState.value = viewState.value?.copy(
                isValidEmail = isValidEmail,
            )
        }
        validateAllFields()
    }

    fun emailFocusChanged(email: String) {
        validator.validateEmail(email) { isValidEmail, emailColor ->
            viewState.value = viewState.value?.copy(
                isValidEmail = isValidEmail,
                emailColor = emailColor,
            )
        }
    }

    fun numberTextChanged(number: String) {
        validator.validateNumber(number) { isValidNumber, _ ->
            viewState.value = viewState.value?.copy(
                isValidNumber = isValidNumber,
            )
        }
        validateAllFields()
    }

    fun numberFocusChanged(number: String) {
        validator.validateNumber(number) { isValidNumber, numberColor ->
            viewState.value = viewState.value?.copy(
                isValidNumber = isValidNumber,
                numberColor = numberColor,
            )
        }
    }

    fun dateTextChanged(date: String) {
        validator.validateDate(date) { isValidDate, _ ->
            viewState.value = viewState.value?.copy(
                isValidDate = isValidDate,
            )
        }
        validateAllFields()
    }

    fun dateFocusChanged(date: String) {
        validator.validateDate(date) { isValidDate, dateColor ->
            viewState.value = viewState.value?.copy(
                isValidDate = isValidDate,
                dateColor = dateColor,
            )
        }
    }

    fun cvcTextChanged(cvc: String) {
        validator.validateCVC(cvc) { isValidCVC, _ ->
            viewState.value = viewState.value?.copy(
                isValidCVC = isValidCVC,
            )
        }
        validateAllFields()
    }

    fun cvcFocusChanged(cvc: String) {
        validator.validateCVC(cvc) { isValidCVC, cvcColor ->
            viewState.value = viewState.value?.copy(
                isValidCVC = isValidCVC,
                cvcColor = cvcColor,
            )
        }
    }

    private fun validateAllFields() {
        with(viewState.value) {
            val validated =
                this?.isValidEmail ?: false && this?.isValidNumber ?: false && this?.isValidDate ?: false && this?.isValidCVC ?: false

            viewState.value = this?.copy(
                isPayButtonEnabled = validated,
            )
        }
    }
}
