package com.iram.playeventvideos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iram.playeventvideos.adapters.EventsListAdapter
import com.iram.playeventvideos.databinding.FragmentHomeBinding
import com.iram.playeventvideos.model.EventSchedule
import com.iram.playeventvideos.utils.autoCleared
import com.iram.playeventvideos.viewmodel.EventScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment(), EventsListAdapter.EventItemListener {

    private lateinit var eventViewModel: EventScheduleViewModel
    private var binding: FragmentHomeBinding by autoCleared()
    private lateinit var eventsListAdapter : EventsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eventViewModel = ViewModelProvider(this).get(EventScheduleViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchData()
    }

    private fun fetchData() {
        eventViewModel.res.observe(viewLifecycleOwner, {
            if(it?.data != null && it.data.isNotEmpty())
                eventsListAdapter.setItems(it.data as ArrayList<EventSchedule>)
        })
    }

    private fun initViews() {
        eventsListAdapter = EventsListAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = eventsListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onClickedItemData(title: String) {
    }
}