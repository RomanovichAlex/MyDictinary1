package by.romanovich.mydictinary.ui.utils

import by.romanovich.mydictinary.data.DataModel

sealed class AppState {
    data class Success(val data: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}

