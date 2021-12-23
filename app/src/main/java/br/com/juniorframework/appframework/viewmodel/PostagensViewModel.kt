package br.com.juniorframework.appframework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.juniorframework.appframework.model.Albuns
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.model.ToDos
import br.com.juniorframework.appframework.repository.local.Preferences
import br.com.juniorframework.appframework.repository.remote.AlbunsRepository
import br.com.juniorframework.appframework.util.APIListener
import br.com.juniorframework.appframework.repository.remote.PostagemRepository
import br.com.juniorframework.appframework.repository.remote.ToDoRepository
import br.com.juniorframework.appframework.util.Constants

class PostagensViewModel(application: Application): AndroidViewModel(application) {

    private val mPostagemList = MutableLiveData<List<Postagens>>()
    val postagemList: LiveData<List<Postagens>> = mPostagemList

    private val mQtdSincronized = MutableLiveData<Int>()
    val qtdSincronized: LiveData<Int> = mQtdSincronized

    private val mFail = MutableLiveData<Boolean>()
    val fail: LiveData<Boolean> = mFail

    private val repositoryPostagens: PostagemRepository = PostagemRepository(application)
    private val repositoryAlbuns: AlbunsRepository = AlbunsRepository(application)
    private val repositoryToDos: ToDoRepository = ToDoRepository(application)

    private val mPreferences = Preferences(application)

    fun initSincronized() {
        if (!mPreferences.getBoolean(Constants.SHARED.SINCRONIZED)) {
            mQtdSincronized.value = 0

            val listenerPostagens = object : APIListener<List<Postagens>> {
                override fun onSuccess(result: List<Postagens>, statusCode: Int) {
                    mQtdSincronized.value = mQtdSincronized.value!!.plus(1)
                }

                override fun onFailure(message: String) {
                    mFail.value = true
                }
            }

            val listenerAlbuns = object : APIListener<List<Albuns>> {
                override fun onSuccess(result: List<Albuns>, statusCode: Int) {
                    mQtdSincronized.value = mQtdSincronized.value!!.plus(1)
                }

                override fun onFailure(message: String) {
                    mFail.value = true
                }
            }

            val listenerTodos = object : APIListener<List<ToDos>> {
                override fun onSuccess(result: List<ToDos>, statusCode: Int) {
                    mQtdSincronized.value = mQtdSincronized.value!!.plus(1)
                }

                override fun onFailure(message: String) {
                    mFail.value = true
                }
            }

            repositoryPostagens.allPostagens(listenerPostagens)
            repositoryAlbuns.allAlbuns(listenerAlbuns)
            repositoryToDos.allToDos(listenerTodos)
        } else {
            list()
        }
    }

    fun mudarSincronizado() {
        mPreferences.saveBoolean(Constants.SHARED.SINCRONIZED, true)
    }

    fun list() {
        mPostagemList.value = repositoryPostagens.list()
    }
}