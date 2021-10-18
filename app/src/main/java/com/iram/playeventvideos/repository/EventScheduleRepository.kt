package com.iram.playeventvideos.repository

import com.iram.playeventvideos.model.Event
import com.iram.playeventvideos.model.Schedule
import com.iram.playeventvideos.network.ServerDataSource
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventScheduleRepository @Inject constructor(private var serverDataSource: ServerDataSource) {

    suspend fun getEvents(): Response<List<Event>> {
        return serverDataSource.getEvents()
    }
    suspend fun getSchedule(): Response<List<Schedule>> {
        return serverDataSource.getSchedule()
    }
}