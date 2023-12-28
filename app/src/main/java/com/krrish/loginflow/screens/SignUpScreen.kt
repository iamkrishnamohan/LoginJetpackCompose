package com.krrish.loginflow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krrish.loginflow.Components.ButtonComponent
import com.krrish.loginflow.Components.CheckboxComponent
import com.krrish.loginflow.Components.ClickableLoginTextComponent
import com.krrish.loginflow.Components.DividerTextComponent
import com.krrish.loginflow.Components.HeadingTextComponent
import com.krrish.loginflow.Components.MyTextFieldComponent
import com.krrish.loginflow.Components.NormalTextComponent
import com.krrish.loginflow.Components.PasswordTextFieldComponent
import com.krrish.loginflow.R
import com.krrish.loginflow.data.LoginViewModel
import com.krrish.loginflow.data.UIEvent
import com.krrish.loginflow.navigation.PostOfficeAppRouter
import com.krrish.loginflow.navigation.Screen

@Composable
fun SignUpScreen(loginViewModel: LoginViewModel = viewModel()) {

    Surface(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.profile),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.FirstNameChanged(it))
                },
                errorStatus = loginViewModel.registrationUiState.value.firstNameError

            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.profile),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.LastNameChanged(it))
                },
                errorStatus = loginViewModel.registrationUiState.value.lastNameError
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.registrationUiState.value.emailError
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.disable),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.registrationUiState.value.passwordError
            )

            CheckboxComponent(
                value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
                    PostOfficeAppRouter.navigateTo(Screen.TermsAndConditions)
                })

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(id = R.string.register),
                onButtonClicked = {
                    loginViewModel.onEvent(UIEvent.RegisterButtonClicked)
                })

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
            })
        }


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}