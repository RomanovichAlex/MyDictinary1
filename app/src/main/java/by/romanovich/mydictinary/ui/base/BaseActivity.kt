package by.romanovich.mydictinary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.romanovich.mydictinary.ui.translator.TranslatorContract
import by.romanovich.mydictinary.ui.utils.AppState


abstract class BaseActivity<T : AppState> : AppCompatActivity(), TranslatorContract.View {
    // Храним ссылку на презентер
    protected lateinit var presenter: TranslatorContract.Presenter<T, TranslatorContract.View>
    protected abstract fun createPresenter(): TranslatorContract.Presenter<T, TranslatorContract.View>
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
}
