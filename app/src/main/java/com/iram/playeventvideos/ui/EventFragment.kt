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
import com.iram.newsheadlines.utils.Resource
import com.iram.playeventvideos.adapters.EventsListAdapter
import com.iram.playeventvideos.databinding.LayoutRviewBinding
import com.iram.playeventvideos.model.EventSchedule
import com.iram.playeventvideos.utils.autoCleared
import com.iram.playeventvideos.viewmodel.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class EventFragment : Fragment(), EventsListAdapter.EventItemListener {

    private lateinit var eventViewModel: EventsViewModel
    private var binding: LayoutRviewBinding by autoCleared()
    private lateinit var eventsListAdapter: EventsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eventViewModel = ViewModelProvider(this).get(EventsViewModel::class.java)
        binding = LayoutRviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchData()
    }

    private fun fetchData() {

        binding.pBar.visibility = View.VISIBLE
        eventViewModel.res.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pBar.visibility = View.GONE
                    Collections.sort(it.data, CustomComparator())
                    if (it?.data != null && it.data.isNotEmpty()) {
                        binding.tvNoData.visibility = View.GONE
                        eventsListAdapter.setItems(it.data)
                    }
                }
                Resource.Status.ERROR -> {
                    if (eventsListAdapter.itemCount == 0)
                        binding.tvNoData.visibility = View.VISIBLE
                }
                Resource.Status.LOADING ->
                    binding.pBar.visibility = View.VISIBLE
            }
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

    class CustomComparator : Comparator<EventSchedule> {
        override fun compare(o1: EventSchedule, o2: EventSchedule): Int {
            return o1.date.compareTo(o2.date)
        }
    }
}