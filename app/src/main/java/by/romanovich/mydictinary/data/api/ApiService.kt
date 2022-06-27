package by.romanovich.mydictinary.data.api

import by.romanovich.mydictinary.data.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
// Обратите внимание, что метод теперь возвращает Deferred
    fun searchAsync(@Query("search") wordToSearch: String):
            Deferred<List<DataModel>>
}


/* @GET("words/search")
 fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}*/
