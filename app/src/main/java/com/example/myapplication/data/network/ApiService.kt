package com.example.myapplication.data.network

import com.example.myapplication.data.model.DoorModelDTO
import com.example.myapplication.data.model.RoomModelDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cameras")
    suspend fun getCamera(): Response<RoomModelDTO>

    @GET("doors")
    suspend fun getDoor(): Response<DoorModelDTO>

}