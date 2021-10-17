package com.iram.playeventvideos.network

import com.iram.playeventvideos.model.Event
import com.iram.playeventvideos.model.Schedule
import retrofit2.Response
import retrofit2.http.GET

interface iService {

    @GET("getEvents")
    suspend fun getEvents(): Response<List<Event>>

    @GET("getSchedule")
    suspend fun getSchedule(): Response<List<Schedule>>
}