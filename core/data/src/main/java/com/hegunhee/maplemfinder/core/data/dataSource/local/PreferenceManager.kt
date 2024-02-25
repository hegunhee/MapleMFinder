package com.hegunhee.maplemfinder.core.data.dataSource.local

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(@ApplicationContext context : Context) {

    private val prefs = context.getSharedPreferences(MapleMKey,Context.MODE_PRIVATE)

    fun getMainOcid() : String {
        return prefs.getString(mainCharacterKey, emptyOcid) ?: emptyOcid
    }

    fun isMainOcid(ocid : String) : Boolean {
        return if(ocid.isBlank()) {
            false
        }else {
            ocid == getMainOcid()
        }
    }

    fun setMainOcid(ocid : String) {
        if(ocid == emptyOcid) return
        prefs.edit {
            if(ocid == getMainOcid()) {
                putString(mainCharacterKey, emptyOcid)
            }else{
                putString(mainCharacterKey, ocid)
            }
            apply()
        }
    }

    companion object {
        private const val MapleMKey = "MapleMSharedPrefence"
        private const val mainCharacterKey = "MainCharacter"
        private const val emptyOcid = ""
    }
}