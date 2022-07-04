package by.romanovich.mydictinary.domain.repository

import by.romanovich.mydictinary.data.AppState


interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}