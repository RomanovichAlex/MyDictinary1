package by.romanovich.mydictinary.di


import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.domain.repository.Repository
import by.romanovich.mydictinary.ui.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
