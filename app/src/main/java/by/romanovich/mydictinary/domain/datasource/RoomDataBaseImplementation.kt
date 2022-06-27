package by.romanovich.mydictinary.domain.datasource

import by.romanovich.mydictinary.data.DataModel


class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
