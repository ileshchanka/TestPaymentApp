package com.example.testpaymentapp

import androidx.annotation.ColorInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testpaymentapp.validation.InputFieldValidator
import com.example.testpaymentapp.validation.UIColorsProvider
import com.example.testpaymentapp.validation.ValidationResult
import com.example.testpaymentapp.validation.ValidationType
import kotlinx.coroutines.launch

class MainViewModel(
    private val validator: InputFieldValidator,
    private val uiColorsProvider: UIColorsProvider,
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
        val isValidEmail: ValidationResult = ValidationResult.Fail,
        val isValidNumber: ValidationResult = ValidationResult.Fail,
        val isValidDate: ValidationResult = ValidationResult.Fail,
        val isValidCVC: ValidationResult = ValidationResult.Fail,
        @ColorInt val emailColor: Int = 0,
        @ColorInt val numberColor: Int = 0,
        @ColorInt val dateColor: Int = 0,
        @ColorInt val cvcColor: Int = 0,
        val isPayButtonEnabled: Boolean = false,
    )

    fun emailTextChanged(email: String) {
        viewModelScope.launch {
            viewState.value = viewState.value?.copy(
                isValidEmail = validator.validate(email, ValidationType.EMAIL),
            )
            validateAllFields()
        }
    }

    fun emailFocusChanged(email: String) {
        viewModelScope.launch {
            val validationResult = validator.validate(email, ValidationType.EMAIL)
            viewState.value = viewState.value?.copy(
                isValidEmail = validationResult,
                emailColor = uiColorsProvider.provideColor(validationResult)
            )
        }
    }

    fun numberTextChanged(number: String) {
        viewModelScope.launch {
            viewState.value = viewState.value?.copy(
                isValidNumber = validator.validate(number, ValidationType.CARD)
            )
        }
        validateAllFields()
    }

    fun numberFocusChanged(number: String) {
        viewModelScope.launch {
            val validationResult = validator.validate(number, ValidationType.CARD)
            viewState.value = viewState.value?.copy(
                isValidNumber = validationResult,
                numberColor = uiColorsProvider.provideColor(validationResult)
            )
        }
    }

    fun dateTextChanged(date: String) {
        viewModelScope.launch {
            viewState.value = viewState.value?.copy(
                isValidDate = validator.validate(date, ValidationType.CARD_DATE)
            )
        }
        validateAllFields()
    }

    fun dateFocusChanged(date: String) {
        viewModelScope.launch {
            val validationResult = validator.validate(date, ValidationType.CARD_DATE)
            viewState.value = viewState.value?.copy(
                isValidDate = validationResult,
                dateColor = uiColorsProvider.provideColor(validationResult)
            )
        }
    }

    fun cvcTextChanged(cvc: String) {
        viewModelScope.launch {
            viewState.value = viewState.value?.copy(
                isValidCVC = validator.validate(cvc, ValidationType.CVC)
            )
        }
        validateAllFields()
    }

    fun cvcFocusChanged(cvc: String) {
        viewModelScope.launch {
            val validationResult = validator.validate(cvc, ValidationType.CVC)
            viewState.value = viewState.value?.copy(
                isValidCVC = validationResult,
                cvcColor = uiColorsProvider.provideColor(validationResult)
            )
        }
    }

    private fun validateAllFields() {
        with(viewState.value) {
            val validated =
                (this?.isValidEmail == ValidationResult.Success) && (this.isValidNumber == ValidationResult.Success) && (this.isValidDate == ValidationResult.Success) && (this.isValidCVC == ValidationResult.Success)

            viewState.value = this?.copy(
                isPayButtonEnabled = validated,
            )
        }
    }
}
