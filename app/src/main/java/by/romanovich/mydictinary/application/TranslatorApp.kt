package by.romanovich.mydictinary.application

import android.app.Application
import by.romanovich.mydictinary.di.application
import by.romanovich.mydictinary.di.mainScreen
import org.koin.core.context.startKoin


// Обратите внимание на dispatchingAndroidInjector и интерфейс Dagger'а
// HasAndroidInjector: мы переопределяем его метод androidInjector. Они
// нужны для внедрения зависимостей в Activity. По своей сути — это вспомогательные
//методы для разработчиков под Андроид для эффективного внедрения компонентов
//платформы, таких как Активити, Сервис и т. п.
class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }


    /*@Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }*/
}
