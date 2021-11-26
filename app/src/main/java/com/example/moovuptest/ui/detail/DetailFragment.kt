package com.example.moovuptest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.moovuptest.databinding.DetailFragmentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailFragment : Fragment(), OnMapReadyCallback {
    private var _binding: DetailFragmentBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.userMap.onCreate(savedInstanceState)
        binding.userMap.onResume()
        binding.userMap.getMapAsync(this)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemUser.userEmail.text = args.dataModel.email
        binding.itemUser.userFullName.text =
            args.dataModel.name.firstName.plus(" " + args.dataModel.name.lastName)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.clear() //clear old markers
        if (args.dataModel.locationModel.longitude != null) {
            val googlePlex = CameraPosition.builder()
                .target(
                    LatLng(
                        args.dataModel.locationModel.latitude,
                        args.dataModel.locationModel.longitude!!
                    )
                )
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null)
            googleMap.addMarker(
                MarkerOptions()
                    .position(
                        LatLng(
                            args.dataModel.locationModel.latitude,
                            args.dataModel.locationModel.longitude!!
                        )
                    )
                    .title(args.dataModel.name.firstName.plus(" " + args.dataModel.name.lastName))
            )

        }

    }

}