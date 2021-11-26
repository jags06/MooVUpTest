package com.example.moovuptest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.moovuptest.databinding.DetailFragmentBinding
import timber.log.Timber

class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("Check the value in detail Fragment ${args.dataModel}")
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemUser.userEmail.text=args.dataModel.email
        binding.itemUser.userFullName.text=args.dataModel.name.firstName.plus(" "+args.dataModel.name.lastName)
    }

}