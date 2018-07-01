package me.androidbox.data.model

data class ProjectEntity(
        val id: String,
        val name: String,
        val fullName: String,
        val starCount: String,
        val dataCreated: String,
        val ownerName: String,
        val ownerAvator: String,
        val isBookedMarked: Boolean) {
}