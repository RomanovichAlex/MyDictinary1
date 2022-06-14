package by.romanovich.mydictinary.model.datasource

import by.romanovich.mydictinary.model.data.DataModel
import by.romanovich.mydictinary.model.datasource.DataSource
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") // To change body of created functions use File
// | Settings | File Templates.
    }
}