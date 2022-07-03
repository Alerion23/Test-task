package com.wenger.mytestapplication.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wenger.mytestapplication.R
import com.wenger.mytestapplication.databinding.WebViewFragmentBinding
import com.wenger.mytestapplication.domain.network.ConnectivityManager
import com.wenger.mytestapplication.ui.MyWebView
import com.wenger.mytestapplication.utils.ConnectionState
import com.wenger.mytestapplication.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WebViewFragment : Fragment(R.layout.web_view_fragment) {

    @Inject
    lateinit var myWebView: MyWebView

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    private var binding: WebViewFragmentBinding? = null
    private val viewModel: WebViewViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WebViewFragmentBinding.bind(view)
        subscribeObservers()
    }

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupView(newUrl: String?) {
        binding?.apply {
            webView.webViewClient = myWebView
            webView.apply {
                if (!newUrl.isNullOrEmpty()) {
                    loadUrl(newUrl)
                } else {
                    loadUrl(Constants.DEFAULT_URL)
                }
                settings.javaScriptEnabled = true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    settings.safeBrowsingEnabled = true
                }
                setOnKeyListener { _, _, keyEvent ->
                    if (keyEvent.keyCode == KeyEvent.KEYCODE_BACK && !webView.canGoBack()) {
                        false
                    } else if (keyEvent.keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action == MotionEvent.ACTION_UP) {
                        goBack()
                        true
                    } else true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        connectivityManager.unregisterConnectionObserver(this)
        binding = null
    }

    private fun subscribeObservers() {

        viewModel.navDirectionLd.observe(this) {
            findNavController().navigate(it)
        }
        viewModel.newUrl.observe(this) {
            setupView(it)
        }
        viewModel.isInternetConnected.observe(this) {
            when (it) {
                is ConnectionState.Available -> {
                }
                is ConnectionState.Unavailable -> {
                    findNavController().navigate(R.id.go_to_no_internet_fragment)
                }
            }
        }
    }
}