package br.com.juniorframework.appframework.util

interface APIListener<T> {
    fun onSuccess(result: T, statusCode: Int)
    fun onFailure(message: String)
}