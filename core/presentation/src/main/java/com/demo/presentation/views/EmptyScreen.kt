package com.demo.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.demo.presentation.R

@Composable
fun RenderEmptyScreen(modifier: Modifier = Modifier, emptyMessage: Int) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Image(
        painter = painterResource(R.drawable.ic_empty),
        contentDescription = "empty-icon",
        modifier = modifier.size(100.dp),
      )
      Spacer(modifier = modifier.height(16.dp))
      Text(
        text = stringResource(id = R.string.no_result),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
      )
      Spacer(modifier = modifier.height(16.dp))
      Text(
        text = stringResource(id = emptyMessage),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
      )
    }
  }
}
