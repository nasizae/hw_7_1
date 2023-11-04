package com.example.myapplication.presentation.door

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.core.base.BaseViewModel
import com.example.myapplication.data.model.DoorModelDTO
import com.example.myapplication.domain.repository.Repository

class DoorViewModel(private val repository: Repository) : BaseViewModel() {

    private val _doors=MutableLiveData<DoorModelDTO>()
    val doors:LiveData<DoorModelDTO> get() = _doors

    fun getDoor()=doOperation(
        operation = {repository.getDoor()},
        success = {_doors.postValue(it)}
    )

}