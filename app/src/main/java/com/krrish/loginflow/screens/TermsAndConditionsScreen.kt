package com.krrish.loginflow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.krrish.loginflow.components.HeadingTextComponent
import com.krrish.loginflow.R
import com.krrish.loginflow.navigation.PostOfficeAppRouter
import com.krrish.loginflow.navigation.Screen
import com.krrish.loginflow.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_heading))
    }
    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview(showSystemUi = true)
@Composable
fun TermsAndConditionsScreenPreview() {
    TermsAndConditionsScreen()
}
