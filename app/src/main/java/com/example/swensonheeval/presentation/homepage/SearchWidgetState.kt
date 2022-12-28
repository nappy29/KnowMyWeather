package com.example.swensonheeval.presentation.homepage

sealed class SearchWidgetState {
    object OPENED : SearchWidgetState()
    object CLOSED : SearchWidgetState()
}