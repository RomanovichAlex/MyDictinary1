package by.romanovich.mydictinary.application

import android.app.Application
import by.romanovich.mydictinary.di.application
import by.romanovich.mydictinary.di.historyScreen
import by.romanovich.mydictinary.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}
