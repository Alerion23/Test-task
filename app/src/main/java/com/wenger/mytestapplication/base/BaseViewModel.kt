package com.wenger.mytestapplication.base

import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    final override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val mainHandler = Handler(Looper.getMainLooper())

    @MainThread
    open fun handleSubscriptionError(e: Throwable): Unit = throw e

    private val subscriptionErrorHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, e ->
            mainHandler.post {
                handleSubscriptionError(e)
            }
        }

    private val subscriptionScope = CoroutineScope(
        Dispatchers.Main +
                SupervisorJob() +
                subscriptionErrorHandler
    )

    fun subscription(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = subscriptionScope.launch(context, start, block)

}