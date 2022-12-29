package com.example.swensonheeval.presentation.search_widget

import com.example.swensonheeval.domain.model.Location

data class LocationSuggestionState(
    val isLoading: Boolean = false,
    var suggestions: List<Location>? = null,
    val error: String? = null
)