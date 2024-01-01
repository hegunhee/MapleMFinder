package com.hegunhee.maplemfinder.core.data.dataSource.local

import android.content.Context
import androidx.core.content.edit
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapleMPreferenceManager @Inject constructor(@ApplicationContext context : Context) {

    private val prefs = context.getSharedPreferences(MapleMKey,Context.MODE_PRIVATE)

    private val moshi = Moshi.Builder().build()

    private val listType = Types.newParameterizedType(List::class.java, String::class.java)

    fun getMainOcid() : String {
        return prefs.getString(mainCharacterKey, emptyOcid) ?: emptyOcid
    }

    fun setMainOcid(ocid : String) {
        prefs.edit {
            putString(mainCharacterKey,ocid)
            apply()
        }
    }

    fun getLikeOcidList() : List<String> {
        val prefsJson = prefs.getString(likeCharacterKey, emptyOcid)
        return if(prefsJson.isNullOrBlank()) {
            emptyList()
        } else {
            prefsJson.fromJson()
        }
    }

    fun addLikeOcid(ocid : String) {
        val ocidList = getLikeOcidList()
        if(ocidList.contains(ocid)) {
            return
        }
        prefs.edit {
            (ocidList + ocid).let { addedList ->
                putString(likeCharacterKey,addedList.toJson())
                apply()
            }
        }
    }

    fun deleteLikeOcid(ocid : String) {
        val ocidList = getLikeOcidList()
        if(!ocidList.contains(ocid)) {
            return
        }
        prefs.edit {
            ocidList.filter { it != ocid }.let { changedList ->
                putString(likeCharacterKey, changedList.toJson())
                apply()
            }
        }
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
        private const val likeCharacterKey = "LikeCharacter"

        private const val emptyOcid = ""
    }
}