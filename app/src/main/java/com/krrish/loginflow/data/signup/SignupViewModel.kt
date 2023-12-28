package com.krrish.loginflow.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.krrish.loginflow.data.RegistrationUiState
import com.krrish.loginflow.data.rules.Validator
import com.krrish.loginflow.navigation.PostOfficeAppRouter
import com.krrish.loginflow.navigation.Screen

class SignupViewModel : ViewModel() {
    private val TAG = SignupViewModel::class.simpleName
    var registrationUiState = mutableStateOf(RegistrationUiState())
    var allValidationsPassed = mutableStateOf(false)
    var signUoInProgress = mutableStateOf(false)
    fun onEvent(event: SignupUIEvent) {
        validateDataWithRules()
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignupUIEvent.LastNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    email = event.email
                )
                printState()
            }

            is SignupUIEvent.PasswordChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUiState.value = registrationUiState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
    }

    private fun signUp() {
        Log.d(TAG, "signUp: ")
        printState()
        createUserInFirebase(
            email = registrationUiState.value.email,
            password = registrationUiState.value.password
        )

    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(fName = registrationUiState.value.firstName)
        val lNameResult = Validator.validateLastName(lName = registrationUiState.value.lastName)
        val emailResult = Validator.validateEmail(email = registrationUiState.value.email)
        val passwordResult =
            Validator.validatePassword(password = registrationUiState.value.password)
        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue =
            registrationUiState.value.privacyPolicyAccepted
        )
        Log.d(
            TAG,
            "validateDataWithRules: $fNameResult $lNameResult $emailResult $passwordResult $privacyPolicyResult"
        )

        registrationUiState.value = registrationUiState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status,
        )
        allValidationsPassed.value =
            fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status && privacyPolicyResult.status
    }

    private fun printState() {
        Log.d(TAG, "printState: ")
        Log.d(TAG, registrationUiState.value.toString())
    }

    private fun createUserInFirebase(email: String, password: String) {
        signUoInProgress.value = true
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "createUserSuccess: ${it.isSuccessful}")
                signUoInProgress.value = false
                if (it.isSuccessful) {
                    PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "createUserFailure: ${it.message} ${it.localizedMessage}")
            }
    }



}