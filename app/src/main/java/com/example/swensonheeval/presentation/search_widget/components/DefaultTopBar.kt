package com.example.swensonheeval.presentation.search_widget.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swensonheeval.common.Utils
import com.example.swensonheeval.presentation.search_widget.LocationSuggestionState

@Composable
fun DefaultTopBar(
    dateString: String, onSearchClicked: () -> Unit
) {
    TopAppBar(backgroundColor = Color.Transparent,
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.dp,
        contentColor = Color.White,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = Utils.getTime(dateString),
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        actions = {
            TopAppBarActionButton(
                imageVector = Icons.Outlined.Search,
                description = "Search"
            ) {
                onSearchClicked()
            }
        }

    )
}


@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchAppBar(
    text: String,
    locationSuggestionState: LocationSuggestionState,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    updateWeatherResult: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = Color.White
    ) {

        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf("") }
        var suggestions by remember {
            mutableStateOf(locationSuggestionState.suggestions ?: emptyList())
        }

        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    selectedOptionText = it
                    onTextChanged(it)
                },
                placeholder = {
                    Text(
                        modifier = Modifier.alpha(ContentAlpha.medium),
                        text = "Search City",
                        color = Color.Black
                    )
                }, textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.subtitle1.fontSize
                ),
                singleLine = true,
                leadingIcon = {
                    IconButton(modifier = Modifier.alpha(ContentAlpha.medium), onClick = {
                        onCloseClicked()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                trailingIcon = {
                    IconButton(modifier = Modifier.alpha(ContentAlpha.medium), onClick = {
                        if (text.isNotEmpty()) {
                            onTextChanged("")
                        } else {
                            onCloseClicked()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Black
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearchClicked(text)
                }),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Black.copy(alpha = ContentAlpha.medium)
                )
            )
            // filter options based on text field value
            if (locationSuggestionState.suggestions?.isNotEmpty() == true) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    locationSuggestionState.suggestions.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                onTextChanged(selectionOption.name)
                                updateWeatherResult(selectionOption.name)
                            }
                        ) {
                            Text(text = "${selectionOption.name} - ${selectionOption.country}")
                        }
                    }
                }
            }
        }
    }

}

