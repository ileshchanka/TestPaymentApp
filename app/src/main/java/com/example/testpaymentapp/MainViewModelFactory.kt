package com.example.testpaymentapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val validator: InputFieldValidator,
    private val resourceProvider: ResourceProvider,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            validator = validator,
            resourceProvider = resourceProvider,
        ) as T
    }
}
