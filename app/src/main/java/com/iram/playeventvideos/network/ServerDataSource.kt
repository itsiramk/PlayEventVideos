package com.iram.playeventvideos.network

import javax.inject.Inject

class ServerDataSource @Inject constructor(
    private val iService: iService
) : BaseDataSource() {

    suspend fun getEvents() = iService.getEvents()
    suspend fun getSchedule() = iService.getSchedule()
}
