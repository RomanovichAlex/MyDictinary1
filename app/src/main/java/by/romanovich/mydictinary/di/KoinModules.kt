package by.romanovich.mydictinary.di

import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.data.RepositoryImplementation
import by.romanovich.mydictinary.domain.datasource.RetrofitImplementation
import by.romanovich.mydictinary.domain.datasource.RoomDataBaseImplementation
import by.romanovich.mydictinary.domain.repository.Repository
import by.romanovich.mydictinary.ui.main.MainInteractor
import by.romanovich.mydictinary.ui.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}

