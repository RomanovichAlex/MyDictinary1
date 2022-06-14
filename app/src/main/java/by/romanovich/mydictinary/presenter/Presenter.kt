package by.romanovich.mydictinary.presenter

import by.romanovich.mydictinary.model.data.AppState
import by.romanovich.mydictinary.view.base.View

// На уровень выше находится презентер, который уже ничего не знает ни о контексте, ни о фреймворке
interface Presenter<T : AppState, V : View> {
    fun attachView(view: V)
    fun detachView(view: V)
    // Получение данных с флагом isOnline(из Интернета или нет)
    fun getData(word: String, isOnline: Boolean)
}