package com.krrish.loginflow.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.krrish.loginflow.data.rules.Validator
import com.krrish.loginflow.navigation.PostOfficeAppRouter
import com.krrish.loginflow.navigation.Screen

class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName
    var loginUiState = mutableStateOf(LoginUIState())
    var allValidationsPassed = mutableStateOf(false)
    var logInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUiState.value = loginUiState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUiState.value = loginUiState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateDataWithRules()
    }

    private fun login() {
        logInProgress.value = true
        val email = loginUiState.value.email
        val password = loginUiState.value.password
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "login: success ${it.isSuccessful}")

                if (it.isSuccessful) {
                    logInProgress.value = false
                    PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
                }
            }.addOnFailureListener {
                Log.e(TAG, "login: Failed ${it.localizedMessage}")
                logInProgress.value = false
            }
    }

    private fun validateDataWithRules() {
        val emailResult = Validator.validateEmail(email = loginUiState.value.email)
        val passwordResult = Validator.validatePassword(password = loginUiState.value.password)

        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }
}