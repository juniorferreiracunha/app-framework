package br.com.juniorframework.appframework.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.juniorframework.appframework.model.Albuns

@Dao
interface AlbunsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(albuns: List<Albuns>)

    @Query("select * from albuns")
    fun list(): List<Albuns>
}