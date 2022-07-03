package com.wenger.mytestapplication.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wenger.mytestapplication.base.BaseViewModel
import com.wenger.mytestapplication.domain.network.ConnectivityManager
import com.wenger.mytestapplication.utils.ConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoInternetViewModel @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : BaseViewModel() {

    val isInternetConnected: LiveData<ConnectionState>
        get() = _isInternetConnected
    private val _isInternetConnected = MutableLiveData<ConnectionState>()

    init {
        subscription {
           connectivityManager.isNetworkAvailable.collect {
               if (!it) {
                   _isInternetConnected.postValue(ConnectionState.Unavailable)
               } else {
                   _isInternetConnected.postValue(ConnectionState.Available)
               }
           }

        }
    }
}