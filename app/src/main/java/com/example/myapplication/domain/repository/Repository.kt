package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.DoorModelDTO
import com.example.myapplication.data.model.RoomModelDTO
import com.example.myapplication.data.network.RemoteDataSource

class Repository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getRoom():Result<RoomModelDTO>{
        return remoteDataSource.getRoom()
    }
    suspend fun getDoor():Result<DoorModelDTO>{
        return remoteDataSource.getDoor()
    }

}