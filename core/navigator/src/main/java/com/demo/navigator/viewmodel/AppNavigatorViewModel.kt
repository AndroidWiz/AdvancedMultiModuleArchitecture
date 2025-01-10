package com.demo.navigator.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.navigator.core.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppNavigatorViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel(), AppNavigator by appNavigator {

}