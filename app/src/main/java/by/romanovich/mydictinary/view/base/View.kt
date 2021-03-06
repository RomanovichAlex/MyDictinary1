package by.romanovich.mydictinary.view.base

import by.romanovich.mydictinary.model.data.AppState

// Нижний уровень. View знает о контексте и фреймворке
interface View {
    // View имеет только один метод, в который приходит некое состояние приложения
    fun renderData(appState: AppState)
}



