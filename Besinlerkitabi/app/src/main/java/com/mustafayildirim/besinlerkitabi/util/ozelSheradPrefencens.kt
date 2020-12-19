package com.mustafayildirim.besinlerkitabi.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class ozelSheradPrefencens {
    companion object{
        private val ZAMAN="zaman"
    private var sharedPreferences:SharedPreferences?=null
        @Volatile private var instance:ozelSheradPrefencens?=null
        private val lock=Any()
        operator fun invoke(context: Context):ozelSheradPrefencens= instance?: synchronized(lock){
            instance?: ozelSharedePreferencesYap(context).also {
                instance=it
            }

        }
        private fun ozelSharedePreferencesYap(context: Context):ozelSheradPrefencens{
                sharedPreferences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return ozelSheradPrefencens()

        }
    }
    fun zamaniKaydet(zaman:Long){
        sharedPreferences?.edit(commit=true){
            putLong(ZAMAN,zaman)
        }

    }
    fun zamaniAl()= sharedPreferences?.getLong(ZAMAN,0)
}