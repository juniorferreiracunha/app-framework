package br.com.juniorframework.appframework.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.juniorframework.appframework.model.Postagens

@Dao
interface PostagensDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(postagens: List<Postagens>)

    @Query("select * from postagens")
    fun list(): List<Postagens>
}