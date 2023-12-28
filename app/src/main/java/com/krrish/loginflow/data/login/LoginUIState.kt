package com.krrish.loginflow.data.login

data class LoginUIState(
    val email: String = "",
    val password: String = "",

    val emailError: Boolean = false,
    val passwordError: Boolean = false
)