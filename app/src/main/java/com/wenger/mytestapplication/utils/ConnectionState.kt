package com.wenger.mytestapplication.utils

sealed class ConnectionState() {

    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}