package by.romanovich.mydictinary.data

import by.romanovich.mydictinary.domain.datasource.DataSource
import by.romanovich.mydictinary.domain.repository.Repository


class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    // Репозиторий возвращает данные, используя dataSource (локальный или
// внешний)
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
