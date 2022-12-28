package com.example.swensonheeval.presentation.search_widget

sealed class SearchWidgetState {
    object OPENED : SearchWidgetState()
    object CLOSED : SearchWidgetState()
}