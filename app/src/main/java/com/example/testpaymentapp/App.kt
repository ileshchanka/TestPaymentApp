package com.example.testpaymentapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.testpaymentapp.di.AppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        AppComponent.init(applicationContext)
    }
}
