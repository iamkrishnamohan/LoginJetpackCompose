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
import com.krrish.loginflow.Components.ButtonComponent
import com.krrish.loginflow.Components.ClickableLoginTextComponent
import com.krrish.loginflow.Components.DividerTextComponent
import com.krrish.loginflow.Components.HeadingTextComponent
import com.krrish.loginflow.Components.MyTextFieldComponent
import com.krrish.loginflow.Components.NormalTextComponent
import com.krrish.loginflow.Components.PasswordTextFieldComponent
import com.krrish.loginflow.Components.UnderLinedTextComponent
import com.krrish.loginflow.R
import com.krrish.loginflow.navigation.PostOfficeAppRouter
import com.krrish.loginflow.navigation.Screen
import com.krrish.loginflow.navigation.SystemBackButtonHandler


@Composable
fun LoginScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.login))

            HeadingTextComponent(value = stringResource(id = R.string.welcome_back))

            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.message),
                onTextSelected = {

                }
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.disable),
                onTextSelected = {

                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(value = stringResource(id = R.string.login),
                onButtonClicked = {
                    
                })

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
            })


        }


    }
    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
    }
}


@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}