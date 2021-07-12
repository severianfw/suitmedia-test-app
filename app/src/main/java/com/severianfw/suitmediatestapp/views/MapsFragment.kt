package com.severianfw.suitmediatestapp.views

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.severianfw.suitmediatestapp.R
import com.severianfw.suitmediatestapp.adapters.HorizontalListEventAdapter
import com.severianfw.suitmediatestapp.models.Event
import com.severianfw.suitmediatestapp.repositories.EventRepository

class MapsFragment : Fragment() {
    private lateinit var rvEvents: RecyclerView
    private var listEvent: ArrayList<Event> = arrayListOf()
    private lateinit var googleMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        // initialize map
        this.googleMap = googleMap

        // add all event marker
        for (i in listEvent.size-1 downTo 0) {
            val event = listEvent[i]

            val eventPosition = LatLng(event.lat, event.long)
            googleMap.addMarker(
                MarkerOptions().position(eventPosition).title("${event.name}")
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventPosition, 15F))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_maps, container, false)
        rvEvents = view.findViewById(R.id.rv_event_horizontal)

        listEvent.addAll(EventRepository.listData)
        showListEvents()

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvEvents)

        // get focused event position
        var position = snapHelper.getSnapPosition(rvEvents)
        rvEvents.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val snapPosition = snapHelper.getSnapPosition(rvEvents)
                    val snapPositionChanged = position != snapPosition
                    if (snapPositionChanged) {
                        position = snapPosition
                    }
                    moveMapCam(position)
                }
            }
        })

        return view
    }



    private fun showListEvents(){
        rvEvents.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HorizontalListEventAdapter(listEvent)
        }
    }

    private fun moveMapCam(position: Int){
        val event = listEvent[position]
        val eventPosition = LatLng(event.lat, event.long)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventPosition, 15F))
    }

    private fun SnapHelper.getSnapPosition(rvEvents: RecyclerView): Int {
        val layoutManager = rvEvents.layoutManager ?: return RecyclerView.NO_POSITION
        val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return layoutManager.getPosition(snapView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}