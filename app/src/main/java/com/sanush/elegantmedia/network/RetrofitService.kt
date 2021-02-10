package com.sanush.elegantmedia.network

import com.sanush.elegantmedia.network.responses.CatResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitService {

    @GET("s/6nt7fkdt7ck0lue/hotels.json") suspend fun get(
    ): CatResponse
}