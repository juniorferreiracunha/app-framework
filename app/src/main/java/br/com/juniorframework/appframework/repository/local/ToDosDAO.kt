package br.com.juniorframework.appframework.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.juniorframework.appframework.model.ToDos

@Dao
interface ToDosDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(toDos: List<ToDos>)

    @Query("select * from todos")
    fun list(): List<ToDos>

    @Query("update todos set completed = :completed where id = :id")
    fun updateCompleted(id: Int, completed: Boolean)
}