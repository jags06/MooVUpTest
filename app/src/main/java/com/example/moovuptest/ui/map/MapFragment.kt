package com.example.moovuptest.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.moovuptest.databinding.FragmentMapBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment() {

    private val mapViewModel: MapViewModel by viewModel()
    private var _binding: FragmentMapBinding? = null

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

        mapViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}