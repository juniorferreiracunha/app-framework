package br.com.juniorframework.appframework.service

import br.com.juniorframework.appframework.model.Albuns
import retrofit2.Call
import retrofit2.http.GET

interface AlbunsService {

    @GET("albums")
    fun getAlbuns(): Call<List<Albuns>>
}