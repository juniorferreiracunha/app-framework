package br.com.juniorframework.appframework.util

interface ClickListener<T> {

    fun onListClick(value: T)

    fun onDeleteClick(value: T)
}