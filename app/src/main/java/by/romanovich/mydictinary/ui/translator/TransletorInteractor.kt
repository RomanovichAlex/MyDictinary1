package by.romanovich.mydictinary.ui.translator

import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.domain.Interactor
import by.romanovich.mydictinary.domain.repository.Repository
import by.romanovich.mydictinary.ui.utils.AppState
import io.reactivex.Observable

class TransletorInteractor(
// Снабжаем интерактор репозиторием для получения локальных или внешних
// данных
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {
    // Интерактор лишь запрашивает у репозитория данные, детали имплементации
// интерактору неизвестны
    override fun getData(word: String, fromRemoteSource: Boolean):
            Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
