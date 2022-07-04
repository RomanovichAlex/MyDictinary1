package by.romanovich.mydictinary.data

import by.romanovich.mydictinary.domain.datasource.DataSourceLocal
import by.romanovich.mydictinary.domain.repository.RepositoryLocal


class RepositoryLocalImpl(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}