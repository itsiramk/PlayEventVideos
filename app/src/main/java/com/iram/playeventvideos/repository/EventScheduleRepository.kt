package com.iram.playeventvideos.repository

import com.iram.playeventvideos.model.EventSchedule
import com.iram.playeventvideos.network.ServerDataSource
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventScheduleRepository @Inject constructor(private var serverDataSource: ServerDataSource) {

    suspend fun getEvents(): Response<ArrayList<EventSchedule>> {
        return serverDataSource.getEvents()
    }
    suspend fun getSchedule(): Response<List<EventSchedule>> {
        return serverDataSource.getSchedule()
    }
}