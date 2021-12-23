package br.com.juniorframework.appframework.service

import br.com.juniorframework.appframework.model.ToDos
import retrofit2.Call
import retrofit2.http.GET

interface ToDosService {

    @GET("todos")
    fun getToDos(): Call<List<ToDos>>
}