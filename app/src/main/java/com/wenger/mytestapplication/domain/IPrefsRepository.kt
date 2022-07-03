package com.wenger.mytestapplication.domain

interface IPrefsRepository {

    suspend fun getIsTermsAccepted(): Boolean

    suspend fun setIsTermsAccepted(isEnabled: Boolean)

    suspend fun setUrlString(url: String?)

    suspend fun getUrlString(): String?

}