package by.romanovich.mydictinary.domain.datasource

import by.romanovich.mydictinary.data.AppState
import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.data.room.HistoryDao
import by.romanovich.mydictinary.domain.utils.convertDataModelSuccessToEntity
import by.romanovich.mydictinary.domain.utils.mapHistoryEntityToSearchResult


class RoomDataBaseImpl(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
// Метод mapHistoryEntityToSearchResult описан во вспомогательном
// классе SearchResultParser, в котором есть и другие методы для
// трансформации данных
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    // Метод сохранения слова в БД. Он будет использоваться в интеракторе
    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
