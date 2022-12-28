package com.example.swensonheeval.presentation.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.swensonheeval.R
import com.example.swensonheeval.presentation.homepage.components.Center
import com.example.swensonheeval.presentation.homepage.components.Footer
import com.example.swensonheeval.presentation.homepage.components.Header
import com.example.swensonheeval.presentation.homepage.components.MainAppBar

@Composable
fun HomeScreen(
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    locationViewModel: LocationSuggestionViewModel = hiltViewModel()
) {
    val weatherState = weatherViewModel.state.value
    val suggestionState = locationViewModel.state.value
    val searchWidgetState by locationViewModel.searchWidgetState
    val searchTextState by locationViewModel.searchTextState

//    Log.d("State", state.forcastObject.toString())
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = "background_image",
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.primary.copy(alpha = 0.88f),
                            MaterialTheme.colors.primaryVariant.copy(alpha = 0.88f)
                        )
                    )
                )
                .fillMaxSize()
        )
        Scaffold(
            backgroundColor = Color.Transparent,   // Make the background transparent
            topBar = {
                MainAppBar(
                    dateString = if (weatherState.forcastObject != null) weatherState.forcastObject.date else "",
                    searchWidgetState = searchWidgetState,
                    searchTextState = searchTextState,
                    locationSuggestionState = suggestionState,
                    updateWeatherResult = {
                        weatherViewModel.getWeatherForecast(it)
                    },
                    onTextChange = {
                        locationViewModel.updateSearchTextState(newValue = it)
                        locationViewModel.getLocationSuggestions()
                    },
                    onCloseClicked = {
                        locationViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
                    },
                    onSearchClicked = {

                    },
                    onSearchTriggered = {
                        locationViewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                    }
                )

            },
        ) {

            if (weatherState.forcastObject != null) {
                val dateObj = weatherState.forcastObject
                Header(
                    cityName = dateObj.location,
                    date = dateObj.date
                )

                Center(dateObj.days[0])
                Footer(dateObj.days)
            }
        }
        if (weatherState.error?.isNotBlank() == true) {
            Text(
                text = weatherState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (weatherState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(ContentAlpha.medium),
                color = Color.White
            )
        }
    }
}