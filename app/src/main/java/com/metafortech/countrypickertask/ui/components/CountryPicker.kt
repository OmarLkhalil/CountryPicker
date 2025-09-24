package com.metafortech.countrypickertask.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import coil3.compose.SubcomposeAsyncImage
import com.metafortech.countrypickertask.ui.views.createCountriesDummyList
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodePicker(
    modifier: Modifier = Modifier,
    selectedCountry: CountryModel?,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onCountrySelected: (CountryModel) -> Unit
) {

    var isSheetOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = phoneNumber,
        onValueChange = { newValue ->
            onPhoneNumberChange(newValue.take(10))
        },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(13.sdp),
        leadingIcon = {
            Row(
                modifier = Modifier
                    .clickable(interactionSource = null, indication = null, onClick = {
                        focusManager.clearFocus()
                        isSheetOpen = true
                    })
                    .height(30.sdp)
                    .padding(start = 8.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubcomposeAsyncImage(
                    model = selectedCountry?.flag.orEmpty(),
                    contentDescription = "Selected Country Flag",
                    modifier = Modifier
                        .width(35.sdp)
                        .height(20.sdp),
                    contentScale = ContentScale.Fit,
                    loading = {
                        CircularProgressIndicator(modifier = Modifier.size(16.sdp))
                    },
                    error = {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Error", tint = Color.Red)
                        Log.e("COIL_IMAGE", "${it.result.throwable}")
                    }
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down Icon",
                    tint = Color.Black
                )
                Text(
                    text = buildString {
                        append("+")
                        append(selectedCountry?.code)
                    },
                    color = Color.Black,
                    fontWeight = Bold,
                    fontSize = 11.ssp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        keyboardActions =KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        placeholder = {
            Text(
                text = "1xxxxxxxxx",
                color = Color.Gray,
                fontSize = 11.ssp
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color.Gray,
            focusedTextColor = Color.Black
        ),
        textStyle = TextStyle(
            fontWeight = Bold,
            fontSize = 12.ssp,
        ),
        singleLine = true
    )

    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = false },
            dragHandle = null,
            sheetState = sheetState
        ) {
            CountriesList(
                onSelected = {
                    onCountrySelected(it)
                    isSheetOpen = false
                }
            )
        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CountriesList(
    onSelected: (CountryModel) -> Unit
) {

    val countries = createCountriesDummyList()

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth()
            .padding(16.sdp),
        verticalArrangement = Arrangement.spacedBy(10.sdp)
    ) {
        items(items = countries, key = { it.id.orEmpty() }) { country ->
            CountryItem(
                country = country,
                onSelected = onSelected
            )
        }
    }
}

fun Int?.orEmpty() = this ?: 0

@Composable
fun CountryItem(
    country: CountryModel,
    onSelected: (CountryModel) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.sdp),
        tonalElevation = 2.sdp,
        modifier = Modifier
            .height(35.sdp)
            .fillMaxWidth(),
        onClick = {
            onSelected(country)
        },
        color = Color.Gray.copy(alpha = 0.1f),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = country.flag,
                contentDescription = "Image of The Countries",
                modifier = Modifier
                    .width(30.sdp)
                    .height(20.sdp),
                contentScale = ContentScale.Fit,
                loading = {
                    CircularProgressIndicator(modifier = Modifier.size(16.sdp))
                },
                error = {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Error", tint = Color.Red)
                    Log.e("COIL_IMAGE", "${it.result.throwable}")
                }
            )
            HSpacer(5)
            Text(
                text = buildString {
                    append("+")
                    append(country.code)
                },
                color = Color.Black,
                fontWeight = Bold,
                style = MaterialTheme.typography.bodyMedium,

                )
        }
    }
}



data class CountryModel(
    val id: Int = 0,
    val code: String = "",
    val flag: String = ""
)
