package com.example.retrofitdemo

data class UsersResponse(
    val count: Int,
    val `data`: List<Users>,
    val error: Boolean
)

data class Users(
    val __v: Int,
    val _id: String,
    val address: List<Any>,
    val created: String,
    val createdAt: String,
    val email: String,
    val firstName: String,
    val mobile: String,
    val name: String,
    val password: String,
    val role: String
)