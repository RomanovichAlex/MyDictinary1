package by.romanovich.mydictinary.ui.main

import by.romanovich.designationOfWords.viewModel.Interactor
import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.di.NAME_LOCAL
import by.romanovich.mydictinary.di.NAME_REMOTE
import by.romanovich.mydictinary.domain.repository.Repository
import by.romanovich.mydictinary.data.AppState
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
    }

}

/*// Снабжаем интерактор репозиторием для получения локальных или внешних
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
}*/
