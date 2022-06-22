package by.romanovich.mydictinary.domain.datasource

import by.romanovich.mydictinary.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") // To change body of created functions use File
// | Settings | File Templates.
    }
}