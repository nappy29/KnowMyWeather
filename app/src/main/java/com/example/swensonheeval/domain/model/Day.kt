package com.example.swensonheeval.domain.model

data class Day(
    var date: String,
    val tempCel: Double,
    val tempFah: Double,
    val condition: String,
    val mph: Double,
    val humidity: Int,
    val imageUrl: String
)