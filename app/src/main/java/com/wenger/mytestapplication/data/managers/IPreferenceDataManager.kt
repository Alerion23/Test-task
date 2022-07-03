package com.wenger.mytestapplication.data.managers

interface IPreferenceDataManager {

    fun getTermsBoolean(): Boolean

    fun setTermsBoolean(isEnabled: Boolean)

    fun setUrlString(url: String?)

    fun getUrlString(): String?

}