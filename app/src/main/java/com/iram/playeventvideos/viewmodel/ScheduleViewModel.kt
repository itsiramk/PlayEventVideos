package com.iram.playeventvideos.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iram.newsheadlines.utils.Resource
import com.iram.playeventvideos.model.Schedule
import com.iram.playeventvideos.repository.EventScheduleRepository
import kotlinx.coroutines.launch

class ScheduleViewModel @ViewModelInject constructor(
    private val eventScheduleRepo: EventScheduleRepository
) : ViewModel() {
    private val _scheduleLiveData = MutableLiveData<Resource<List<Schedule>?>>()

    val res: LiveData<Resource<List<Schedule>?>>
        get() = _scheduleLiveData

    init {
        getEventSchedule()
    }

    private fun getEventSchedule() = viewModelScope.launch {
        _scheduleLiveData.postValue(Resource.loading(null))
        eventScheduleRepo.getSchedule().let {
            if (it.isSuccessful) {
                _scheduleLiveData.postValue(Resource.success(it.body()))
            } else {
                _scheduleLiveData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}
