package com.martha.user.base

import android.util.Log
import com.martha.user.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> handlingApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return  withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable: Throwable) {
                when(throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Log.e("err", throwable.localizedMessage)
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}