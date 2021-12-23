package br.com.juniorframework.appframework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.juniorframework.appframework.model.Albuns
import br.com.juniorframework.appframework.repository.remote.AlbunsRepository

class AlbunsViewModel(application: Application): AndroidViewModel(application) {

    private val mAlbunsList = MutableLiveData<List<Albuns>>()
    val albunsList: LiveData<List<Albuns>> = mAlbunsList

    private val repositoryAlbuns: AlbunsRepository = AlbunsRepository(application)

    fun list() {
        mAlbunsList.value = repositoryAlbuns.list()
    }
}