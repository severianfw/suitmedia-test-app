package com.severianfw.suitmediatestapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.severianfw.suitmediatestapp.adapters.HorizontalListEventAdapter
import com.severianfw.suitmediatestapp.adapters.ListEventAdapter
import com.severianfw.suitmediatestapp.models.Event
import com.severianfw.suitmediatestapp.repositories.EventRepository

class EventMapsFragment : Fragment() {
    private var listEvent: ArrayList<Event> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val rvEvents: RecyclerView = view.findViewById(R.id.rv_event_horizontal)
        listEvent.addAll(EventRepository.listData)
        rvEvents.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HorizontalListEventAdapter(listEvent)
        }
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvEvents)


        return view
    }
}