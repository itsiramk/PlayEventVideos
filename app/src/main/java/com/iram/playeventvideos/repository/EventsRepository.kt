package com.iram.playeventvideos.repository

import com.iram.playeventvideos.model.EventSchedule
import com.iram.playeventvideos.network.ServerDataSource
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventsRepository @Inject constructor(private var serverDataSource: ServerDataSource) {

    suspend fun getEventSchedule(): Response<List<EventSchedule>> {
        return serverDataSource.getEventSchedule()
    }
}