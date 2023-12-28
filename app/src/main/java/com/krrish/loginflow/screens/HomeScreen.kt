package com.krrish.loginflow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krrish.loginflow.R
import com.krrish.loginflow.components.AppToolbar
import com.krrish.loginflow.components.ButtonComponent
import com.krrish.loginflow.components.HeadingTextComponent
import com.krrish.loginflow.data.SignupViewModel


@Composable
fun HomeScreen(signupViewModel: SignupViewModel = viewModel()) {

    Scaffold(
        topBar = {
            AppToolbar(toolbarTitle = stringResource(id = R.string.home),
                logoutButtonClicked = {
                    signupViewModel.logout()
                })
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {


            }
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}