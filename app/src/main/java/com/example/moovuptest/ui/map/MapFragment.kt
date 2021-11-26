package com.example.moovuptest.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moovuptest.BaseViewModel
import com.example.moovuptest.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val baseViewModel: BaseViewModel by viewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.userMap.onCreate(savedInstanceState)
        binding.userMap.onResume()
        binding.userMap.getMapAsync(this)
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.clear() //clear old markers
        val googlePlex = CameraPosition.builder()
            .target(LatLng(22.3479143, 113.698436))
            .zoom(10f)
            .bearing(0f)
            .tilt(45f)
            .build()

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null)

        baseViewModel.users.observe(viewLifecycleOwner) { users ->
            users?.let { it ->
                it.filter { it.locationModel.longitude != null }.forEach {
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(
                                LatLng(
                                    it.locationModel.latitude,
                                    it.locationModel.longitude!!
                                )
                            )
                            .title(it.name.firstName.plus(" " + it.name.lastName))
                    )
                }
            }
        }
    }
}