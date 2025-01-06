package com.demo.login.presentation.view

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.demo.login.R
import com.demo.login.presentation.flow.LoginInput
import com.demo.login.presentation.flow.LoginOutput
import com.demo.login.presentation.flow.LoginViewState
import com.demo.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
  modifier: Modifier = Modifier,
  loginViewModel: LoginViewModel,
  loginViewState: LoginViewState = LoginViewState(),
) {

  // react to view output events
  LaunchedEffect(loginViewModel) {
    loginViewModel.viewOutput.collect{output ->
      when(output){
        is LoginOutput.NavigateToHome -> TODO()
        is LoginOutput.NavigateToRegister -> TODO()
        is LoginOutput.ShowError -> TODO()
      }
    }
  }


  Surface(modifier = modifier.fillMaxSize()) {
    Column(
      modifier = modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      // user name
      CustomTextField(
        modifier = modifier,
        label = stringResource(id = R.string.username_label),
        value = loginViewState.userName,
        errorMessage = stringResource(id = loginViewState.userNameError.getErrorMessage()),
        showError = loginViewState.showUsernameError(),
      ) { username ->
        loginViewModel.setInput(LoginInput.UsernameUpdated(userName = username))
      }
      Spacer(modifier.height(16.dp))

      // password
      CustomTextField(
        modifier = modifier,
        label = stringResource(id = R.string.password_label),
        value = loginViewState.password,
        errorMessage = stringResource(id = loginViewState.passwordError.getErrorMessage()),
        showError = loginViewState.showPasswordError(),
      ) { password ->
        loginViewModel.setInput(LoginInput.PasswordUpdated(password = password))
      }
      Spacer(modifier.height(16.dp))

      // login button
      Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { loginViewModel.login() },
      ) {
        Text(text = "Login")
      }
      Spacer(modifier = modifier.height(16.dp))

      TextButton(onClick = { loginViewModel.setInput(LoginInput.RegisterButtonClicked) }) {
        Text(text = "Register here!")
      }
    }
  }
}

@Composable
fun CustomTextField(
  modifier: Modifier,
  label: String,
  value: String,
  showError: Boolean,
  errorMessage: String,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  onChanged: (String) -> Unit,
) {
  OutlinedTextField(
    value = value,
    onValueChange = { onChanged(it) },
    label = { Text(text = label) },
    isError = showError,
    visualTransformation = visualTransformation,
    modifier = modifier
      .fillMaxWidth()
      .padding(8.dp),
  )
  if (showError) {
    Text(
      text = errorMessage,
      color = Color.Red,
      modifier = modifier.padding(all = 8.dp),
    )
  }
}
