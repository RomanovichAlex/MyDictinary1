package by.romanovich.designationOfWords.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.romanovich.mydictinary.domain.rx.SchedulerProvider
import by.romanovich.mydictinary.data.AppState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(

    protected open val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected open val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected open val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {
    // Наследуемся от ViewModel
// Метод, благодаря которому Activity подписывается на изменение данных,
// возвращает LiveData, через которую и передаются данные
    abstract fun getData(word: String, isOnline: Boolean)

    // Единственный метод класса ViewModel, который вызывается перед
// уничтожением Activity
    override fun onCleared() {
        compositeDisposable.clear()
    }
}

