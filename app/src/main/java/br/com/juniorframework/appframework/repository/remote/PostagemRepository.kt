package br.com.juniorframework.appframework.repository.remote

import android.content.Context
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.db.PostagemDatabase
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.service.PostagemService
import br.com.juniorframework.appframework.service.RetrofitClient
import br.com.juniorframework.appframework.util.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostagemRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.createService(PostagemService::class.java)
    private val postagemDAO = PostagemDatabase.getDatabase(context).postagensDAO()

    fun allPostagens(listener: APIListener<List<Postagens>>) {

        verificarConexao(listener)

        val call: Call<List<Postagens>> = remote.getPostagens()
        call.enqueue(object : Callback<List<Postagens>> {
            override fun onResponse(call: Call<List<Postagens>>, response: Response<List<Postagens>>) {
                val code = response.code()
                if (fail(code)) {
                    listener.onFailure(failRespose(response.errorBody()!!.string()))
                } else {
                    response.body()?.let {
                        listener.onSuccess(it, code)
                        postagemDAO.save(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Postagens>>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_POSTAGENS))
            }
        })
    }

    fun verificarConexao(listener: APIListener<List<Postagens>>) {
        if (!isConnectionAvailable(mContext)) {
            listener.onFailure(mContext.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
    }

    fun list() = postagemDAO.list()
}