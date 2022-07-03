package com.wenger.mytestapplication.utils

import kotlinx.coroutines.*

suspend inline fun <T> ioContext(
    noinline block: suspend CoroutineScope.() -> T
): T = withContext(Dispatchers.IO, block)

@Suppress("NOTHING_TO_INLINE")
inline fun CoroutineScope.launchIo(
    noinline block: suspend CoroutineScope.() -> Unit
): Job = launch(
    context = Dispatchers.IO,
    block = block
)