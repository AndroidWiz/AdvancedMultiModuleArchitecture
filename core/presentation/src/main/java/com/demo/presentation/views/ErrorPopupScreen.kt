package com.demo.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.demo.domain.model.ErrorMessage
import com.demo.presentation.R

@Composable
fun RenderErrorPopupScreen(
  modifier: Modifier = Modifier,
  errorMessage: ErrorMessage,
  retryAction: () -> Unit,
) {
  var showDialog by remember { mutableStateOf(true) }

  fun dismissDialog() {
    showDialog = false
  }

  if (showDialog) {
    Dialog(
      onDismissRequest = {},
      properties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
      ),
      content = {
        Box(
          modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
          contentAlignment = Alignment.Center,
        ) {
          Box(
            modifier = modifier
              .aspectRatio(1f)
              .fillMaxWidth(0.8f)
              .background(color = Color.White)
              .padding(16.dp),
            contentAlignment = Alignment.Center,
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Image(
                painter = painterResource(R.drawable.ic_error),
                contentDescription = "error-icon",
                modifier = modifier.size(100.dp),
              )
              Spacer(modifier = modifier.height(16.dp))
              Text(
                text = stringResource(id = R.string.cannot_proceed),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
              )
              Spacer(modifier = modifier.height(16.dp))
              Text(
                text = errorMessage.message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
              )
              Spacer(modifier = modifier.height(16.dp))
              Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth(),
              ) {
                // retry button
                Button(
                  onClick = { retryAction() },
                  modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1f),
                ) {
                  Text(text = stringResource(id = R.string.retry_again))
                }
                // dismiss button
                Button(
                  onClick = { dismissDialog() },
                  modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1f),
                ) {
                  Text(text = stringResource(id = R.string.dismiss_again))
                }
              }
            }
          }
        }
      },
    )
  }
}
