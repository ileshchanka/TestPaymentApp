package com.example.testpaymentapp.validation

import com.example.testpaymentapp.ResourceProvider
import javax.inject.Inject

interface UIColorsProvider {

    fun provideColor(validationResult: ValidationResult): Int
}

class UIColorsProviderImpl @Inject constructor(
    private val resourceProvider: ResourceProvider,
) : UIColorsProvider {

    override fun provideColor(validationResult: ValidationResult) =
        when (validationResult) {
            ValidationResult.Success -> resourceProvider.getColor(android.R.color.black)
            ValidationResult.Fail -> resourceProvider.getColor(android.R.color.holo_red_dark)
        }
}
