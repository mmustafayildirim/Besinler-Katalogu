package com.mustafayildirim.besinlerkitabi.servicepackage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mustafayildirim.besinlerkitabi.model.Besin
@Dao
interface besinDAO {
    @Insert
    suspend fun insertAll(vararg besin:Besin):List<Long>
    //vararg birden fazla besin objesini tanımlar,listlong idleri alır
    @Query("SELECT * FROM besin")
    suspend fun getAllBesin():List<Besin>
    @Query("SELECT * FROM besin WHERE uuid=:besinId")
    suspend fun getBesin(besinId:Int):Besin
    @Query("DELETE FROM besin")
    suspend fun deleteAllBesin()

}