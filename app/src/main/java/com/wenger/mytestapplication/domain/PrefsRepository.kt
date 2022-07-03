package com.wenger.mytestapplication.domain

import com.wenger.mytestapplication.data.managers.PreferenceDataManager
import com.wenger.mytestapplication.utils.ioContext
import javax.inject.Inject

class PrefsRepository @Inject constructor(
    private val dataManager: PreferenceDataManager
) : IPrefsRepository {

    override suspend fun getIsTermsAccepted(): Boolean = ioContext {
        dataManager.getTermsBoolean()
    }

    override suspend fun setIsTermsAccepted(isEnabled: Boolean) {
        ioContext {
            dataManager.setTermsBoolean(isEnabled)
        }
    }

    override suspend fun setUrlString(url: String?) {
        ioContext {
            dataManager.setUrlString(url)
        }
    }

    override suspend fun getUrlString(): String? = ioContext {
        dataManager.getUrlString()
    }
}