package com.example.myapplication.data.model

data class RoomModelDTO(
    val `data`: Data,
    val success: Boolean
){
    data class Camera(
        val favorites: Boolean,
        val id: Int,
        val name: String,
        val rec: Boolean,
        val room: String,
        val snapshot: String
    )
    data class Data(
        val cameras: List<Camera>,
        val room: List<String>
    )
}