package com.example.myapplication.data.network

import com.example.myapplication.core.base.BaseDataSource
import com.example.myapplication.data.model.DoorModelDTO
import com.example.myapplication.data.model.RoomModelDTO

class RemoteDataSource(private var apiService: ApiService) : BaseDataSource() {

    suspend fun getRoom(): Result<RoomModelDTO> {
        return getResult {
            apiService.getCamera()
        }
    }
    suspend fun getDoor():Result<DoorModelDTO>{
        return getResult {
            apiService.getDoor()
        }
    }
}