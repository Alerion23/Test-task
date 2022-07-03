package com.wenger.mytestapplication.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wenger.mytestapplication.base.BaseViewModel
import com.wenger.mytestapplication.domain.IPrefsRepository
import com.wenger.mytestapplication.utils.launchIo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: IPrefsRepository
) : BaseViewModel() {

    fun agreeTermsAndConditions() {
        launchIo {
            repository.setIsTermsAccepted(true)
        }
    }
}