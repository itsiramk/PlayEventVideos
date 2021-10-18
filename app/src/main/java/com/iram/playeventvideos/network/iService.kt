package com.iram.playeventvideos.network

import com.iram.playeventvideos.model.EventSchedule
import org.jetbrains.annotations.Async
import retrofit2.Response
import retrofit2.http.GET

interface iService {

    @GET("getEvents")
    suspend fun getEvents(): Response<List<EventSchedule>>

    @GET("getSchedule")
    suspend fun getSchedule(): Response<List<EventSchedule>>
}