package com.demo.mypet.model

sealed class ResultModel<out T> {
    data class Success<out T>(val value: T) : ResultModel<T>()
    data class GenericError(val code: Int? = null, val error: Any? = null) :
        ResultModel<Nothing>()

    object NetworkError : ResultModel<Nothing>()
}