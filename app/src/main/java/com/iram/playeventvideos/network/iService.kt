package com.iram.playeventvideos.network

import com.iram.playeventvideos.model.EventSchedule
import retrofit2.Response
import retrofit2.http.GET

interface iService {

    @GET("getSchedule")
    suspend fun getSchedule(): Response<List<EventSchedule>>
}