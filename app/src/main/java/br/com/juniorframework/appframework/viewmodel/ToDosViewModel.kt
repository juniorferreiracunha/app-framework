package br.com.juniorframework.appframework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.juniorframework.appframework.model.ToDos
import br.com.juniorframework.appframework.repository.remote.ToDoRepository

class ToDosViewModel(application: Application): AndroidViewModel(application) {

    private val mTodosList = MutableLiveData<List<ToDos>>()
    val todosList: LiveData<List<ToDos>> = mTodosList

    private val repositoryToDos: ToDoRepository = ToDoRepository(application)

    fun list() {
        mTodosList.value = repositoryToDos.list()
    }

    fun updateCompleted(id: Int, completed: Boolean) {
        repositoryToDos.updateCompleted(id, !completed)
        list()
    }
}