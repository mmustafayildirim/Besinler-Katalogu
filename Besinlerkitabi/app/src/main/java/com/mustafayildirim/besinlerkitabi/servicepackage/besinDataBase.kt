package com.mustafayildirim.besinlerkitabi.servicepackage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mustafayildirim.besinlerkitabi.model.Besin

@Database(entities = arrayOf(Besin::class),version = 1)
abstract class besinDataBase :RoomDatabase(){
    abstract fun besinDao():besinDAO
    companion object{
       @Volatile private var instance:besinDataBase?=null
        private val lock=Any()
        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?: dataBaseOlustur(context).also {
                instance=it
            }
        }

        private fun dataBaseOlustur(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            besinDataBase::class.java,"besinDataBase").build()

    }



}