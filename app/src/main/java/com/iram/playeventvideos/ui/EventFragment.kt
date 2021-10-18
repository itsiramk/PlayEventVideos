package com.iram.playeventvideos.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iram.playeventvideos.adapters.EventsListAdapter
import com.iram.playeventvideos.databinding.FragmentEventBinding
import com.iram.playeventvideos.utils.autoCleared
import com.iram.playeventvideos.viewmodel.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment(), EventsListAdapter.EventItemListener {

    private lateinit var eventViewModel: EventsViewModel
    private var binding: FragmentEventBinding by autoCleared()
    private lateinit var eventsListAdapter: EventsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eventViewModel = ViewModelProvider(this).get(EventsViewModel::class.java)
        binding = FragmentEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchData()
    }

    private fun fetchData() {
        eventViewModel.res.observe(viewLifecycleOwner, {
            if (it?.data != null && it.data.isNotEmpty())
                eventsListAdapter.setItems(it.data)
        })
    }

    private fun initViews() {
        eventsListAdapter = EventsListAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = eventsListAdapter
    }

    override fun onClickedItemData(videoUrl: String) {
        if (videoUrl.isNotEmpty()) {
            activity?.let {
                val intent = Intent(it, VideoActivity::class.java)
                intent.putExtra("video", videoUrl)
                it.startActivity(intent)
            }
        } else {
            Toast.makeText(context, "No Video to play!!", Toast.LENGTH_LONG).show()
        }

    }
}