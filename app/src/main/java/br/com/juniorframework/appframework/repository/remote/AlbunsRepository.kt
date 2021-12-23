package br.com.juniorframework.appframework.repository.remote

import android.content.Context
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.db.PostagemDatabase
import br.com.juniorframework.appframework.model.Albuns
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.service.AlbunsService
import br.com.juniorframework.appframework.service.PostagemService
import br.com.juniorframework.appframework.service.RetrofitClient
import br.com.juniorframework.appframework.util.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbunsRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.createService(AlbunsService::class.java)
    private val albunsDAO = PostagemDatabase.getDatabase(context).albunsDAO()

    fun allAlbuns(listener: APIListener<List<Albuns>>) {

        verificarConexao(listener)

        val call: Call<List<Albuns>> = remote.getAlbuns()
        call.enqueue(object : Callback<List<Albuns>> {
            override fun onResponse(call: Call<List<Albuns>>, response: Response<List<Albuns>>) {
                val code = response.code()
                if (fail(code)) {
                    listener.onFailure(failRespose(response.errorBody()!!.string()))
                } else {
                    response.body()?.let {
                        listener.onSuccess(it, code)
                        albunsDAO.save(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Albuns>>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_POSTAGENS))
            }
        })
    }

    fun verificarConexao(listener: APIListener<List<Albuns>>) {
        if (!isConnectionAvailable(mContext)) {
            listener.onFailure(mContext.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
    }

    fun list() = albunsDAO.list()
}