package com.wenger.mytestapplication.domain.network

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ConnectivityManager @Inject constructor(
    private val context: Context
) {

    private val connectionLiveData = ConnectionLiveData(context)

    private val _isNetworkAvailable = MutableStateFlow<Boolean>(false)
    val isNetworkAvailable = _isNetworkAvailable.asStateFlow()

    fun registerConnectionObserver(lifecycleOwner: LifecycleOwner) {
        connectionLiveData.observe(lifecycleOwner, { isConnected ->
            isConnected?.let { _isNetworkAvailable.value = it }
        })
    }

    fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner) {
        connectionLiveData.removeObservers(lifecycleOwner)
    }
}