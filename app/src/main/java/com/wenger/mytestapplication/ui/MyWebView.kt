package com.wenger.mytestapplication.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import com.wenger.mytestapplication.domain.IPrefsRepository
import com.wenger.mytestapplication.utils.launchIo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MyWebView @Inject constructor(
    private val repository: IPrefsRepository
) : WebViewClient(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onPageFinished(view: WebView?, url: String?) {
        launchIo {
            repository.setUrlString(url)
        }
    }
}