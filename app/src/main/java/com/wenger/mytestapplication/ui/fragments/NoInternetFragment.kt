package com.wenger.mytestapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wenger.mytestapplication.R
import com.wenger.mytestapplication.databinding.NoInternetFragmentBinding
import com.wenger.mytestapplication.domain.network.ConnectivityManager
import com.wenger.mytestapplication.utils.ConnectionState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoInternetFragment : Fragment(R.layout.no_internet_fragment) {

    @Inject
    lateinit var connectivityManager: ConnectivityManager
    private val viewModel: NoInternetViewModel by viewModels()
    private var binding: NoInternetFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NoInternetFragmentBinding.bind(view)
        subscribeObservers()
    }

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        connectivityManager.unregisterConnectionObserver(this)
        binding = null
    }

    private fun subscribeObservers() {
        viewModel.isInternetConnected.observe(this) {
            when (it) {
                is ConnectionState.Available -> {
                    findNavController().navigate(R.id.go_to_web_reconnect)
                }
                is ConnectionState.Unavailable -> {
                }
            }
        }
    }
}