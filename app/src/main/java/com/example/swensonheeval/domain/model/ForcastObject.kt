package com.example.swensonheeval.domain.model

data class ForcastObject(
    val date: String,
    val location: String,
    val days: List<Day>
)