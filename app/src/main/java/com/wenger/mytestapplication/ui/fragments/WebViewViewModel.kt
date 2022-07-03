package com.wenger.mytestapplication.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wenger.mytestapplication.R
import com.wenger.mytestapplication.base.BaseViewModel
import com.wenger.mytestapplication.domain.IPrefsRepository
import com.wenger.mytestapplication.domain.network.ConnectivityManager
import com.wenger.mytestapplication.utils.ConnectionState
import com.wenger.mytestapplication.utils.SingleLiveEvent
import com.wenger.mytestapplication.utils.launchIo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class WebViewViewModel @Inject constructor(
    private val repository: IPrefsRepository,
    private val connectivityManager: ConnectivityManager
) : BaseViewModel() {

    val newUrl: LiveData<String?>
        get() = _newUrl
    private val _newUrl = SingleLiveEvent<String?>()

    val navDirectionLd: LiveData<Int>
        get() = _navDirectionLd
    private val _navDirectionLd = SingleLiveEvent<Int>()

    val isInternetConnected: LiveData<ConnectionState>
        get() = _isInternetConnected
    private val _isInternetConnected = MutableLiveData<ConnectionState>()


    init {
        viewModelScope.launchIo {
            val isTermsWatched = repository.getIsTermsAccepted()
            if (!isTermsWatched) {
                _navDirectionLd.postValue(R.id.go_to_start_fragment)
            }
        }
        viewModelScope.launchIo {
            val url = repository.getUrlString()
            _newUrl.postValue(url)
        }
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