package com.example.myapplication.presentation.door

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.myapplication.R
import com.example.myapplication.data.model.DoorModelDTO
import com.example.myapplication.data.network.RemoteDataSource
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.databinding.FragmentDoorBinding
import com.example.myapplication.domain.repository.Repository
import com.example.myapplication.presentation.utils.SwipeItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoorFragment : Fragment() {
    private lateinit var binding: FragmentDoorBinding
    private val adapter = DoorAdapter()
    private val retrofitClient = RetrofitClient().createApiService()
    private val remoteDataSource = RemoteDataSource(retrofitClient)
    private val repository = Repository(remoteDataSource)
    private val doorViewModel = DoorViewModel(repository)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoorBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipe()
        initLiveData()
        initView()
    }

    private fun initView() {
        doorViewModel.getDoor()
    }

    private fun initLiveData() {
        doorViewModel.doors.observe(viewLifecycleOwner) {
            getData(it.data)
        }
        doorViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.shimmer.startShimmer()
                binding.shimmer.visibility = View.VISIBLE
            } else {
                binding.shimmer.stopShimmer()
                binding.shimmer.visibility = View.GONE
            }
        }
        doorViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData(doorModel: List<DoorModelDTO.Data>) {
        adapter.addData(doorModel)
        binding.rvDoor.adapter = adapter
        binding.rvDoor.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initSwipe() {
        val itemTouchHelper = ItemTouchHelper(object : SwipeItem(binding.rvDoor) {
            override fun instantiateUnderlayButton(position: Int): List<Button> {
                val favoritesButton = favoritesButton()
                return listOf(favoritesButton, EditButton())
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvDoor)
    }

    private fun favoritesButton(): SwipeItem.Button {
        return SwipeItem.Button(
            requireContext(),
            "Fav",
            20f,
            R.drawable.star,
        )
    }

    private fun EditButton(): SwipeItem.Button {
        return SwipeItem.Button(
            requireContext(),
            "Edit",
            20f,
            R.drawable.edit,
        )
    }

}



