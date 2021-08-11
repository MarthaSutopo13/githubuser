package com.martha.muridkreatif.network.api

import com.martha.muridkreatif.network.model.User
import com.martha.user.data.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("/users")
    suspend fun getListUser(
        @Query("per_page") page: Int
    ): List<User>

    @GET("/search/users")
    suspend fun getListUserByName(
            @Query("q") search: String,
            @Query("per_page") page: Int
    ): UserResponse
}