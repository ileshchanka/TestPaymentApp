package com.example.testpaymentapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testpaymentapp.validation.InputFieldValidator
import com.example.testpaymentapp.validation.UIColorsProvider
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val validator: InputFieldValidator,
    private val uiColorsProvider: UIColorsProvider,
    private val resourceProvider: ResourceProvider,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            validator = validator,
            uiColorsProvider = uiColorsProvider,
            resourceProvider = resourceProvider,
        ) as T
    }
}
