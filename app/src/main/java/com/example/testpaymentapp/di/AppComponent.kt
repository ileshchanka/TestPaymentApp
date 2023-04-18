package com.example.testpaymentapp.di

import android.content.Context
import com.example.testpaymentapp.App
import com.example.testpaymentapp.MainActivity
import com.example.testpaymentapp.di.modules.ToolsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ToolsModule::class,
    ],
)
@Singleton
interface AppComponent : AppProvider {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }

    fun inject(where: App)
    fun inject(where: MainActivity)

    companion object {

        private var component: AppComponent? = null

        fun init(context: Context) {
            component = DaggerAppComponent.builder()
                .context(context)
                .build().apply {
                    component = this
                }
        }

        fun get(): AppComponent {
            return component ?: throw NotImplementedError("You should call 'init' method")
        }
    }
}

interface AppProvider {}
