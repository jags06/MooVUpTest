package com.example.moovuptest.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moovuptest.BaseViewModel
import com.example.moovuptest.databinding.FragmentListBinding
import com.example.moovuptest.model.DataModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ListFragment : Fragment(), UserListAdapter.OnItemClickListener {

    private var _binding: FragmentListBinding? = null
    private val baseViewModel: BaseViewModel by viewModel()
    private val userListAdapter = UserListAdapter(arrayListOf(), this)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.usersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        baseViewModel.users.observe(viewLifecycleOwner, { users ->
            users?.let {
                binding.usersList.visibility = View.VISIBLE
                userListAdapter.updateUserList(it)
            }
        })
        baseViewModel.usersLoadError.observe(viewLifecycleOwner, { isError ->
            binding.listError.visibility = if (isError == "") View.GONE else View.VISIBLE
        })
        baseViewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.usersList.visibility = View.GONE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(adapterPosition: Int) {
        Timber.d("Check position ${baseViewModel.users.value?.get(adapterPosition)}")
        val dataModel: DataModel = baseViewModel.users.value?.get(adapterPosition)!!

        val action = ListFragmentDirections.actionNavigationListToNavigationDetail(dataModel)
        findNavController().navigate(action)
    }
}