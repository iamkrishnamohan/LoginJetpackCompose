package com.krrish.loginflow.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.krrish.loginflow.data.rules.Validator

class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName
    var registrationUiState = mutableStateOf(RegistrationUiState())

    fun onEvent(event: UIEvent) {
        validateDataWithRules()
        when (event) {
            is UIEvent.FirstNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is UIEvent.LastNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is UIEvent.EmailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    email = event.email
                )
                printState()
            }

            is UIEvent.PasswordChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )
                printState()
            }

            is UIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        Log.d(TAG, "signUp: ")
        printState()
        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(fName = registrationUiState.value.firstName)
        val lNameResult = Validator.validateLastName(lName = registrationUiState.value.lastName)
        val emailResult = Validator.validateEmail(email = registrationUiState.value.email)
        val passwordResult =
            Validator.validatePassword(password = registrationUiState.value.password)
        Log.d(TAG, "validateDataWithRules: $fNameResult $lNameResult $emailResult $passwordResult")

        registrationUiState.value = registrationUiState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )
    }

    private fun printState() {
        Log.d(TAG, "printState: ")
        Log.d(TAG, registrationUiState.value.toString())
    }
}