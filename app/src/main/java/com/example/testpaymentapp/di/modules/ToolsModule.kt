package com.example.testpaymentapp.di.modules

import com.example.testpaymentapp.MainViewModel
import com.example.testpaymentapp.ResourceProvider
import com.example.testpaymentapp.ResourceProviderImpl
import com.example.testpaymentapp.validation.InputFieldValidator
import com.example.testpaymentapp.validation.InputFieldValidatorImpl
import com.example.testpaymentapp.validation.UIColorsProvider
import com.example.testpaymentapp.validation.UIColorsProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface ToolsModule {

    @Binds
    @Singleton
    fun providesResourceProvider(impl: ResourceProviderImpl): ResourceProvider

    @Binds
    @Singleton
    fun provideFieldsValidator(impl: InputFieldValidatorImpl): InputFieldValidator

    @Binds
    @Singleton
    fun provideColorProvider(impl: UIColorsProviderImpl): UIColorsProvider

    companion object {
        @Provides
        @Singleton
        fun provideMainViewModel(
            inputFieldValidator: InputFieldValidator,
            uiColorsProvider: UIColorsProvider,
            resourceProvider: ResourceProvider,
        ): MainViewModel {
            return MainViewModel(
                validator = inputFieldValidator,
                uiColorsProvider = uiColorsProvider,
                resourceProvider = resourceProvider,
            )
        }
    }
}
