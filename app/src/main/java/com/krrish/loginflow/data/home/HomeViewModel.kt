package com.krrish.loginflow.data.home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.krrish.loginflow.data.NavigationItem
import com.krrish.loginflow.navigation.PostOfficeAppRouter
import com.krrish.loginflow.navigation.Screen

class HomeViewModel : ViewModel() {
    private val TAG = HomeViewModel::class.simpleName

    val navigationItemsList = listOf<NavigationItem>(
        NavigationItem(
            title = "Home", icon = Icons.Default.Home,
            description = "Home Screen", itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Settings", icon = Icons.Default.Settings,
            description = "Settings Screen", itemId = "settingsScreen"
        ),
        NavigationItem(
            title = "Favorite", icon = Icons.Default.Favorite,
            description = "Favorite Screen", itemId = "favoriteScreen"
        )
    )

    fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "logout: success")
                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(TAG, "logout: not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }
}