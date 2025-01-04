package com.demo.login.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.demo.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier, loginViewModel: LoginViewModel) {
  var userName by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  Surface(modifier = modifier.fillMaxSize()) {
    Column(
      modifier = modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      // user name
      OutlinedTextField(
        value = userName,
        onValueChange = { userName = it },
        label = { Text(text = "User Name") },
        modifier = modifier
          .fillMaxWidth()
          .padding(8.dp),
      )

      // password
      OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(text = "Password") },
        modifier = modifier
          .fillMaxWidth()
          .padding(8.dp),
      )

      // login button
      Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { loginViewModel.login() },
      ) {
        Text(text = "Login")
      }
    }
  }
}
