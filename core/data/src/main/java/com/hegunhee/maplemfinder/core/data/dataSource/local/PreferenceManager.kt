package com.hegunhee.maplemfinder.core.data.dataSource.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(@ApplicationContext context : Context) {

    private val prefs = context.getSharedPreferences(MapleMKey,Context.MODE_PRIVATE)

    private val moshi = Moshi.Builder().build()

    private val listType = Types.newParameterizedType(List::class.java, String::class.java)

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

    fun getFavoriteOcidList() : List<String> {
        val prefsJson = prefs.getString(favoriteCharacterKey, emptyOcid)
        return if(prefsJson.isNullOrBlank()) {
            emptyList()
        } else {
            prefsJson.fromJson()
        }
    }

    fun isFavoriteOcid(ocid : String) :Boolean {
        val ocidList = getFavoriteOcidList()
        return if(ocidList.isEmpty()) {
            false
        }else {
            ocidList.contains(ocid)
        }
    }

    fun isFavoriteListEmpty() : Boolean {
        return getFavoriteOcidList().isEmpty()
    }

    fun toggleFavoriteOcid(ocid: String) {
        val ocidList = getFavoriteOcidList()
        if (ocid == emptyOcid) return
        prefs.edit {
            if (ocidList.contains(ocid)) {
                deleteFavoriteOcid(ocid, ocidList)
            } else {
                addFavoriteOcid(ocid, ocidList)
            }
            apply()
        }
    }

    fun getHistoryOcidList() : List<String> {
        val prefsJson = prefs.getString(historyCharacterKey, emptyOcid)
        return if(prefsJson.isNullOrBlank()) {
            emptyList()
        } else {
            prefsJson.fromJson()
        }
    }

    fun addHistoryOcid(ocid : String) {
        val ocidList = getHistoryOcidList()
        if(ocid == emptyOcid) return
        prefs.edit {
            if (ocidList.contains(ocid)) {
                return
            }else {
                putString(historyCharacterKey,(ocidList + ocid).toJson())
            }
        }
    }

    fun deleteHistoryOcid(ocid : String) {
        val ocidList = getHistoryOcidList()
        if(ocid == emptyOcid) return
        prefs.edit {
            putString(historyCharacterKey,ocidList.filter { it != ocid }.toJson())
        }
    }

    private fun SharedPreferences.Editor.addFavoriteOcid(ocid : String, ocidList : List<String>) {
        putString(favoriteCharacterKey, (ocidList + ocid).toJson())
    }

    private fun SharedPreferences.Editor.deleteFavoriteOcid(ocid : String, ocidList : List<String>) {
        putString(favoriteCharacterKey, ocidList.filter { it != ocid }.toJson())
    }

    private fun List<String>.toJson() : String {
        return moshi.adapter<List<String>>(listType).toJson(this)
    }

    private fun String.fromJson() : List<String> {
        return moshi.adapter<List<String>>(listType).fromJson(this) ?: emptyList()
    }

    companion object {
        private const val MapleMKey = "MapleMSharedPrefence"
        private const val mainCharacterKey = "MainCharacter"
        private const val favoriteCharacterKey = "FavoriteCharacter"
        private const val historyCharacterKey = "HistoryCharacter"
        private const val emptyOcid = ""
    }
}