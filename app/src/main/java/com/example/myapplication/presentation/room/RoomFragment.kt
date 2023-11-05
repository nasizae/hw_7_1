package com.example.myapplication.presentation.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.myapplication.R
import com.example.myapplication.data.model.RoomModelDTO
import com.example.myapplication.data.network.RemoteDataSource
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.databinding.FragmentRoomBinding
import com.example.myapplication.domain.repository.Repository
import com.example.myapplication.presentation.utils.SwipeItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment() {

    private lateinit var binding: FragmentRoomBinding
    private val retrofitClient = RetrofitClient().createApiService()
    private val remoteDataSource = RemoteDataSource(retrofitClient)
    private val repository = Repository(remoteDataSource)
    private val roomViewModel = RoomViewModel(repository)
    private val adapter = RoomAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()
        initView()
        initSwipe()

    }

    private fun initView() {
        roomViewModel.gerRoom()
    }

    private fun initLiveData() {
        roomViewModel.rooms.observe(viewLifecycleOwner) {
            getData(it.data.cameras)
        }
        roomViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.shimmer.startShimmer()
                binding.shimmer.visibility = View.VISIBLE
            } else {
                binding.shimmer.stopShimmer()
                binding.shimmer.visibility = View.GONE
            }
        }
        roomViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData(roomModel: List<RoomModelDTO.Camera>) {
        adapter.addData(roomModel)
        binding.rvCamera.adapter = adapter
    }

    private fun initSwipe() {
        val itemTouchHelper = ItemTouchHelper(object : SwipeItem(binding.rvCamera) {
            override fun instantiateUnderlayButton(position: Int): List<Button> {
                val favoritesButton = favoritesButton()
                return listOf(favoritesButton)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvCamera)
    }

    private fun favoritesButton(): SwipeItem.Button {
        return SwipeItem.Button(
            requireContext(),
            "Fav",
            20f,
            R.drawable.star,
        )
    }
}