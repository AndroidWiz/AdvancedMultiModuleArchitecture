package com.demo.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.navigator.viewmodel.AppNavigatorViewModel

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier) {
  var userName by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var confirmPassword by remember { mutableStateOf("") }

  val appNavigatorViewModel: AppNavigatorViewModel = hiltViewModel()

  Surface(modifier = modifier.fillMaxSize()) {
    Column(
      modifier = modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      // user name
      OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        label = { Text(text = stringResource(id = R.string.username_label)) },
        value = userName,
        onValueChange = { userName = it },
      )
      Spacer(modifier.height(16.dp))

      // email
      OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        label = { Text(text = stringResource(id = R.string.email_label)) },
        value = email,
        onValueChange = { email = it },
      )
      Spacer(modifier.height(16.dp))

      // password
      OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        label = { Text(text = stringResource(id = R.string.password_label)) },
        value = password,
        onValueChange = { password = it },
      )

      // confirm password
      OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        label = { Text(text = stringResource(id = R.string.confirm_password_label)) },
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
      )

      // login button
      Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { },
      ) {
        Text(text = "Register")
      }
      Spacer(modifier = modifier.height(16.dp))

      TextButton(onClick = { appNavigatorViewModel.popBackStack() }) {
        Text(text = "Already have an account? Sign In here!")
      }
    }
  }
}
