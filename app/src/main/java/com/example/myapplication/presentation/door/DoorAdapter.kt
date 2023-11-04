package com.example.myapplication.presentation.door

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.myapplication.data.model.DoorModelDTO
import com.example.myapplication.databinding.ItemDoorBinding

class DoorAdapter():Adapter<DoorAdapter.DoorHolder>() {

    private val _doors= mutableListOf<DoorModelDTO.Data>()
    val doors:List<DoorModelDTO.Data>get() = _doors

    fun addData(doorModelDTO: List<DoorModelDTO.Data>){
        _doors.clear()
        _doors.addAll(doorModelDTO)
        notifyItemRangeChanged(_doors.size,doorModelDTO.size-_doors.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorHolder {
       return DoorHolder(ItemDoorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()=doors.size

    override fun onBindViewHolder(holder: DoorHolder, position: Int) {
        holder.bind(doors[position])
    }
    inner class DoorHolder(private var binding: ItemDoorBinding):ViewHolder(binding.root) {
            fun bind(dooModel: DoorModelDTO.Data){
                    binding.imgDoor.load(dooModel.snapshot)
                    binding.name.text=dooModel.name
                itemView.setOnClickListener {
                    if(binding.imgDoor.visibility==View.GONE){
                        binding.imgDoor.visibility=View.VISIBLE
                    }
                    else{
                        binding.imgDoor.visibility=View.GONE
                    }
                }
            }

    }
}