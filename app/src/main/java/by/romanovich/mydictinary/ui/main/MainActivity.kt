package by.romanovich.mydictinary.ui.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.romanovich.designationOfWords.utils.isOnline
import by.romanovich.mydictinary.R
import by.romanovich.mydictinary.data.DataModel
import by.romanovich.mydictinary.databinding.ActivityMainBinding
import by.romanovich.mydictinary.ui.base.BaseActivity
import by.romanovich.mydictinary.ui.main.adapter.MainAdapter
import by.romanovich.mydictinary.ui.translator.TranslationFragment
import by.romanovich.mydictinary.data.AppState
import dagger.android.AndroidInjection
import javax.inject.Inject

// Контракта уже нет
class MainActivity : BaseActivity<AppState, MainInteractor>() {
    // Внедряем фабрику для создания ViewModel
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var model: MainViewModel

    // Создаём модель
    /*override val model: MainViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }*/
    // Паттерн Observer в действии. Именно с его помощью мы подписываемся на
// изменения в LiveData
    private val observer = Observer<AppState> { renderData(it) }
    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(
                    this@MainActivity, data.text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Сообщаем Dagger’у, что тут понадобятся зависимости
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Фабрика уже готова, можно создавать ViewModel
        model = viewModelFactory.create(MainViewModel::class.java)
        model.subscribe().observe(this@MainActivity, observer)





        binding.searchFab.setOnClickListener {
            val searchDialogFragment = TranslationFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                TranslationFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
// Обратите внимание на этот ключевой момент. У ViewModel
// мы получаем LiveData через метод getData и подписываемся
// на изменения, передавая туда observer
                    /* model.getData(searchWord, true).observe(this@MainActivity,
                         observer)*/
                    isNetworkAvailable = isOnline(applicationContext)
                    if (isNetworkAvailable) {
                        model.getData(searchWord, isNetworkAvailable)
                    } else {
                        Toast.makeText(
                            this@MainActivity, "Device is offline",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
            searchDialogFragment.show(
                supportFragmentManager,
                BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
            )
        }
    }

    // Удаляем ненужные вспомогательные методы типа createPresenter. Всё остальное - без изменений, за исключением одной детали:
    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.subscribe().observe(this@MainActivity, observer)
/*// В случае ошибки мы повторно запрашиваем данные и подписываемся
// на изменения
        model.getData("hi", true).observe(this@MainActivity, observer)*/
        }
    }
    /* private fun showErrorScreen(error: String?) {
         showViewError()
         binding.errorTextview.text = error ?: getString(R.string.undefined_error)
         binding.reloadButton.setOnClickListener {
             presenter.getData("hi", true)
         }
     }*/

    /*override fun createPresenter(): Presenter<AppState, View> {
        return MainPresenterImpl()
    }*/


    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter !!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

}