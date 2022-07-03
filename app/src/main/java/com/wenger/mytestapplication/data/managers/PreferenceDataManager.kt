package com.wenger.mytestapplication.data.managers

import com.wenger.mytestapplication.data.Prefs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceDataManager @Inject constructor(private val prefs: Prefs) : IPreferenceDataManager {

    override fun getTermsBoolean(): Boolean {
        return prefs.getIsTermsAccepted()
    }

    override fun setTermsBoolean(isEnabled: Boolean) {
        prefs.setIsTermsAccepted(isEnabled)
    }

    override fun setUrlString(url: String?) {
        prefs.setLastUrl(url)
    }

    override fun getUrlString(): String? {
        return prefs.getLastUrl()
    }

}