package com.example.testpaymentapp.di.modules

import com.example.testpaymentapp.InputFieldValidator
import com.example.testpaymentapp.MainViewModel
import com.example.testpaymentapp.ResourceProvider
import com.example.testpaymentapp.ResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface ToolsModule {

    @Binds
    @Singleton
    fun providesResourceProvider(impl: ResourceProviderImpl): ResourceProvider

    companion object {
        @Provides
        @Singleton
        fun provideMainViewModel(
            inputFieldValidator: InputFieldValidator,
            resourceProvider: ResourceProvider,
        ): MainViewModel {
            return MainViewModel(
                validator = inputFieldValidator,
                resourceProvider = resourceProvider,
            )
        }
    }
}
