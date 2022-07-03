package com.wenger.mytestapplication.data

import android.content.Context

class Prefs(private val context: Context) {

    fun setIsTermsAccepted(isEnable: Boolean) {
        val prefs = context.getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE).edit()
        prefs.putBoolean(TERMS_BOOLEAN, isEnable)
        prefs.apply()
    }

    fun getIsTermsAccepted(): Boolean {
        val prefs = context.getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE)
        return prefs.getBoolean(TERMS_BOOLEAN, false)
    }

    fun setLastUrl(url: String?) {
        val prefs = context.getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE).edit()
        prefs.putString(LAST_URL, url)
        prefs.apply()
    }

    fun getLastUrl() : String? {
        val prefs = context.getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE)
        return prefs.getString(LAST_URL,"")
    }

    companion object {
        private const val SHARED_FILE = "my_prefs"
        private const val TERMS_BOOLEAN = "terms_boolean"
        private const val LAST_URL = "last_url"
    }
}