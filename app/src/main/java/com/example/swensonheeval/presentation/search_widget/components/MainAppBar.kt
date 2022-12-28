package com.example.swensonheeval.presentation.search_widget.components

import androidx.compose.runtime.Composable
import com.example.swensonheeval.presentation.search_widget.LocationSuggestionState
import com.example.swensonheeval.presentation.search_widget.SearchWidgetState

@Composable
fun MainAppBar(
    dateString: String,
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