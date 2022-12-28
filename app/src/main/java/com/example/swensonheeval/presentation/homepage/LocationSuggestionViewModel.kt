package com.example.swensonheeval.presentation.homepage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swensonheeval.common.Resource
import com.example.swensonheeval.domain.use_case.get_locations.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LocationSuggestionViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(LocationSuggestionState())
    val state: State<LocationSuggestionState> = _state

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: MutableState<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: MutableState<String> = _searchTextState

    fun getLocationSuggestions() {
        if (searchTextState.value.length > 2) {
            getLocationsUseCase(searchTextState.value).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = LocationSuggestionState(suggestions = result.data ?: null)
                    }
                    is Resource.Error -> {
                        _state.value = LocationSuggestionState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = LocationSuggestionState(isLoading = true)
                    }
                }

            }.launchIn(viewModelScope)
        }
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}