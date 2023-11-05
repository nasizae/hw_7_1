package com.example.myapplication.data.model

data class DoorModelDTO(
    val data: List<Data>,
    val success: Boolean
) {
    data class Data(
        val favorites: Boolean,
        val id: Int,
        val name: String,
        val room: String,
        val snapshot: String
    )
}