package com.iram.playeventvideos.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iram.newsheadlines.utils.Resource
import com.iram.playeventvideos.model.Event
import com.iram.playeventvideos.repository.EventsRepository
import kotlinx.coroutines.launch

class EventsViewModel @ViewModelInject constructor(
    private val eventsRepo: EventsRepository
) : ViewModel() {
    private val _eventLiveData = MutableLiveData<Resource<List<Event>?>>()

    val res: LiveData<Resource<List<Event>?>>
        get() = _eventLiveData

    init {
        getEventSchedule()
    }

    private fun getEventSchedule() = viewModelScope.launch {
        _eventLiveData.postValue(Resource.loading(null))
        eventsRepo.getEvents().let {
            if (it.isSuccessful) {
                _eventLiveData.postValue(Resource.success(it.body()))
            } else {
                _eventLiveData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}
