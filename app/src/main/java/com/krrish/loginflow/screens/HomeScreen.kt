package com.krrish.loginflow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krrish.loginflow.R
import com.krrish.loginflow.components.ButtonComponent
import com.krrish.loginflow.components.HeadingTextComponent
import com.krrish.loginflow.data.SignupViewModel

@Composable
fun HomeScreen(signupViewModel: SignupViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeadingTextComponent(value = stringResource(R.string.home))

            ButtonComponent(value = stringResource(R.string.logout), onButtonClicked = {
                signupViewModel.logout()
            }, isEnabled = true)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}