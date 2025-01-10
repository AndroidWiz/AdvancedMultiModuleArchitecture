package com.demo.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.demo.domain.model.toUser
import com.demo.navigator.destinations.USER_AGE
import com.demo.navigator.destinations.USER_FULL_NAME
import com.demo.navigator.destinations.USER_PARAMETER

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {

    val backStackEntry = navController.currentBackStackEntryAsState().value
    val user = backStackEntry?.arguments?.getString(USER_PARAMETER)
    val fullName = backStackEntry?.arguments?.getString(USER_FULL_NAME)
    val age = backStackEntry?.arguments?.getInt(USER_AGE)
    val userObj = user?.toUser()


    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // email
            Text(text = "Email: ${userObj?.email}")
            Spacer(modifier = modifier.padding(16.dp))

            // full name
            Text(text = "Full Name: $fullName")
            Spacer(modifier = modifier.padding(16.dp))

            // age
            Text(text = "Age: $age")
            Spacer(modifier = modifier.padding(16.dp))

            // button
            Button(onClick = {}) {
                Text(text = "Load more info!")
            }
        }
    }
}