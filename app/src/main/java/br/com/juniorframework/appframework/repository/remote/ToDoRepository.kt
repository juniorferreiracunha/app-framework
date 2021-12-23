package br.com.juniorframework.appframework.repository.remote

import android.content.Context
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.db.PostagemDatabase
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.model.ToDos
import br.com.juniorframework.appframework.service.PostagemService
import br.com.juniorframework.appframework.service.RetrofitClient
import br.com.juniorframework.appframework.service.ToDosService
import br.com.juniorframework.appframework.util.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDoRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.createService(ToDosService::class.java)
    private val todosDAO = PostagemDatabase.getDatabase(context).toDosDAO()

    fun allToDos(listener: APIListener<List<ToDos>>) {

        verificarConexao(listener)

        val call: Call<List<ToDos>> = remote.getToDos()
        call.enqueue(object : Callback<List<ToDos>> {
            override fun onResponse(call: Call<List<ToDos>>, response: Response<List<ToDos>>) {
                val code = response.code()
                if (fail(code)) {
                    listener.onFailure(failRespose(response.errorBody()!!.string()))
                } else {
                    response.body()?.let {
                        listener.onSuccess(it, code)
                        todosDAO.save(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<ToDos>>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_POSTAGENS))
            }
        })
    }

    fun verificarConexao(listener: APIListener<List<ToDos>>) {
        if (!isConnectionAvailable(mContext)) {
            listener.onFailure(mContext.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
    }

    fun list() = todosDAO.list()

    fun updateCompleted(id: Int, completed: Boolean) = todosDAO.updateCompleted(id, completed)
}