package com.example.myapplication.presentation.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.myapplication.data.model.RoomModelDTO
import com.example.myapplication.databinding.ItemCameraBinding

class RoomAdapter() : Adapter<RoomAdapter.CameraHolder>() {

    private var _rooms= mutableListOf<RoomModelDTO.Camera>()
    val rooms:List<RoomModelDTO.Camera>get() = _rooms

    fun addData(roomModel: List<RoomModelDTO.Camera>){
        _rooms.clear()
        _rooms.addAll(roomModel)
        notifyItemRangeChanged(_rooms.size,roomModel.size-_rooms.size)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraHolder {
       return CameraHolder(ItemCameraBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = rooms.size

    override fun onBindViewHolder(holder: CameraHolder, position: Int) {
        holder.bind(rooms[position])
    }

    inner class CameraHolder(private val binding: ItemCameraBinding) : ViewHolder(binding.root) {
        fun bind(roomModel: RoomModelDTO.Camera) {
            binding.numberCamera.text = roomModel.name
            binding.imgCamera.load(roomModel.snapshot)
        }

    }
}