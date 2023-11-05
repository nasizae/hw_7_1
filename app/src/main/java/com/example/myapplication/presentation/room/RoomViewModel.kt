package com.example.myapplication.presentation.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.core.base.BaseViewModel
import com.example.myapplication.data.model.RoomModelDTO
import com.example.myapplication.domain.repository.Repository

class RoomViewModel(private val repository: Repository) : BaseViewModel() {
    private val _rooms = MutableLiveData<RoomModelDTO>()
    val rooms: LiveData<RoomModelDTO> get() = _rooms

    fun gerRoom() = doOperation(
        operation = { repository.getRoom() },
        success = { _rooms.postValue(it) }
    )
}

