package com.suitmedia.km.test.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): UserDto
}