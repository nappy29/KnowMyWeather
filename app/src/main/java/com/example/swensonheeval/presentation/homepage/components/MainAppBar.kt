package com.example.swensonheeval.presentation.homepage.components

import androidx.compose.runtime.Composable
import com.example.swensonheeval.presentation.homepage.LocationSuggestionState
import com.example.swensonheeval.presentation.homepage.SearchWidgetState

@Composable
fun MainAppBar(
    dateString:String,
    searchWidgetState: SearchWidgetState,
    locationSuggestionState: LocationSuggestionState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    updateWeatherResult: (String) -> Unit
) {
    when (searchWidgetState) {
        is SearchWidgetState.CLOSED -> {
            DefaultTopBar(
                dateString = dateString,
                onSearchClicked = onSearchTriggered
            )
        }
        is SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                locationSuggestionState = locationSuggestionState,
                onTextChanged = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked,
                updateWeatherResult = updateWeatherResult
            )
        }
    }
}