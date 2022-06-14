package by.romanovich.mydictinary.ui.translator

import by.romanovich.mydictinary.ui.utils.AppState

class TranslatorContract {

    // Нижний уровень. View знает о контексте и фреймворке
    interface View {
        // View имеет только один метод, в который приходит некое состояние приложения
        fun renderData(appState: AppState)
    }

    // На уровень выше находится презентер, который уже ничего не знает ни о контексте, ни о фреймворке
    interface Presenter<T : AppState, V : View> {
        fun attachView(view: V)
        fun detachView(view: V)

        // Получение данных с флагом isOnline(из Интернета или нет)
        fun getData(word: String, isOnline: Boolean)
    }
}