package com.example.swensonheeval.presentation.homepage

import com.example.swensonheeval.domain.model.Location

data class LocationSuggestionState (
    val isLoading: Boolean = false,
    val suggestions: List<Location>? = null,
    val error: String? = null
    )