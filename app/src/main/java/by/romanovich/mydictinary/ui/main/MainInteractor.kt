package by.romanovich.mydictinary.ui.main

import by.romanovich.designationOfWords.viewModel.Interactor
import by.romanovich.mydictinary.data.AppState
import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.domain.repository.Repository


class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
