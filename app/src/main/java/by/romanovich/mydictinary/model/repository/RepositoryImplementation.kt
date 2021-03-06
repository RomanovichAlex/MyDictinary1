package by.romanovich.mydictinary.model.repository

import by.romanovich.mydictinary.model.data.DataModel
import by.romanovich.mydictinary.model.datasource.DataSource
import by.romanovich.mydictinary.model.repository.Repository
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    // Репозиторий возвращает данные, используя dataSource (локальный или
// внешний)
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
