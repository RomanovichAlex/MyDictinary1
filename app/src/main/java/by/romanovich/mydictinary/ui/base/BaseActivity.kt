package by.romanovich.mydictinary.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.romanovich.designationOfWords.utils.isOnline
import by.romanovich.designationOfWords.viewModel.BaseViewModel
import by.romanovich.designationOfWords.viewModel.Interactor
import by.romanovich.mydictinary.data.AppState


abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (! isNetworkAvailable) {
            Toast.makeText(
                this, "Device is offline",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    //abstract class BaseActivity<T : AppState> : AppCompatActivity() {
// В каждой Активити будет своя ViewModel, которая наследуется от BaseViewModel
    abstract val model: BaseViewModel<T>

    // Каждая Активити будет отображать какие-то данные в соответствующем состоянии
    abstract fun renderData(appState: T)
}


//MVP
/*// Храним ссылку на презентер
protected lateinit var presenter: Presenter<T, View>
protected abstract fun createPresenter(): Presenter<T, View>
abstract override fun renderData(appState: AppState)
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter = createPresenter()
}
// Когда View готова отображать данные, передаём ссылку на View в презентер
override fun onStart() {
    super.onStart()
    presenter.attachView(this)
}
// При пересоздании или уничтожении View удаляем ссылку, иначе в презентере
// будет ссылка на несуществующую View
override fun onStop() {
    super.onStop()
    presenter.detachView(this)
}
}*/
