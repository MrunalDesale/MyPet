package com.demo.mypet.rest.util

import android.util.Log
import com.demo.mypet.model.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

object NetworkUtil {
    suspend inline fun <T> makeApiCall(
        crossinline callFunction: suspend () -> T
    ): ResultModel<T>? {
        return try {
            val successResult = withContext(Dispatchers.IO) {
                callFunction.invoke()
            }
            ResultModel.Success(successResult)
        } catch (throwable: Throwable) {
            var data = ResultModel.GenericError()
            withContext(Dispatchers.Main) {
                throwable.printStackTrace()
                Log.e(
                    "NetworkUtils",
                    "Call error: ${throwable.localizedMessage}",
                    throwable.cause
                )
                when (throwable) {
                    is IOException -> ResultModel.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = throwable.response()?.errorBody() ?: ""
                        data = ResultModel.GenericError(code, errorResponse)
                    }
                    else -> {
                        data = ResultModel.GenericError(null, null)
                    }
                }
            }
            data
        }
    }
}