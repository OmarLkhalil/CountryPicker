package com.metafortech.countrypickertask.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.metafortech.countrypickertask.R
import com.metafortech.countrypickertask.ui.components.CountryCodePicker
import com.metafortech.countrypickertask.ui.components.CountryModel
import com.metafortech.countrypickertask.ui.components.ScreenContainer
import com.metafortech.countrypickertask.ui.components.VSpacer
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp


@Composable
fun RegisterScreen() {

    var phoneNumber by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf(createCountriesDummyList().first()) }
    var isAgree by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.sdp, vertical = 40.sdp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.one_last_step),
            fontSize = 24.ssp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        VSpacer(height = 18)
        Text(
            text = stringResource(R.string.please_login_or_signup_for_a),
            fontSize = 12.ssp,
            color = Color.Black
        )
        Row {
            Text(
                text = stringResource(R.string.free),
                fontSize = 12.ssp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = stringResource(R.string.account_to_continue),
                fontSize = 12.ssp,
                color = Color.Black
            )
        }
        VSpacer(height = 25)
        CountryCodePicker(
            selectedCountry = selectedCountry,
            phoneNumber = phoneNumber,
            onPhoneNumberChange = { phoneNumber = it },
            onCountrySelected = { selectedCountry = it }
        )
        VSpacer(height = 5)
        Text(
            text = stringResource(R.string.we_will_use_this_to_verify_your_account),
            fontSize = 9.ssp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 10.sdp, start = 2.sdp),
            color = Color.Gray
        )
        VSpacer(height = 26)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isAgree,
                onCheckedChange = {
                    isAgree = it
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Blue,
                    uncheckedColor = Color.Gray
                )
            )

            Row {
                Text(
                    text = stringResource(R.string.i_agree_and_comply_to_the),
                    fontSize = 9.ssp,
                    color = Color.Black
                )
                Text(
                    text = stringResource(R.string.community_guidelines),
                    fontSize = 9.ssp,
                    color = Color.Blue.copy(alpha = 0.5f)
                )
            }
        }
    }
}

fun createCountriesDummyList(): List<CountryModel> {
    return listOf(
        CountryModel(1, "20", "https://flagcdn.com/w320/eg.png"),
        CountryModel(2, "966", "https://flagcdn.com/w320/sa.png"),
        CountryModel(3, "971", "https://flagcdn.com/w320/ae.png"),
        CountryModel(4, "965", "https://flagcdn.com/w320/kw.png"),
        CountryModel(5, "974", "https://flagcdn.com/w320/qa.png"),
        CountryModel(6, "973", "https://flagcdn.com/w320/bh.png"),
        CountryModel(7, "962", "https://flagcdn.com/w320/jo.png"),
        CountryModel(8, "961", "https://flagcdn.com/w320/lb.png")
    )
}

@Preview
@Composable
fun PreviewRegister() {
    ScreenContainer {
        RegisterScreen()
    }
}