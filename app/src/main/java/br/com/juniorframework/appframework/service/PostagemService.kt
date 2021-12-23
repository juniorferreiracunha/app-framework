package br.com.juniorframework.appframework.service

import br.com.juniorframework.appframework.model.Postagens
import retrofit2.Call
import retrofit2.http.GET

interface PostagemService {

    @GET("posts")
    fun getPostagens(): Call<List<Postagens>>
}