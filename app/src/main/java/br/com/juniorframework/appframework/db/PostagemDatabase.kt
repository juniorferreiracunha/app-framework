package br.com.juniorframework.appframework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.juniorframework.appframework.model.Albuns
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.model.ToDos
import br.com.juniorframework.appframework.repository.local.AlbunsDAO
import br.com.juniorframework.appframework.repository.local.PostagensDAO
import br.com.juniorframework.appframework.repository.local.ToDosDAO

@Database(entities = [Postagens::class, Albuns::class, ToDos::class], version = 1)
abstract class PostagemDatabase : RoomDatabase() {

    abstract fun postagensDAO(): PostagensDAO
    abstract fun albunsDAO(): AlbunsDAO
    abstract fun toDosDAO(): ToDosDAO

    companion object {
        private lateinit var INSTANCE: PostagemDatabase

        fun getDatabase(context: Context): PostagemDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(PostagemDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, PostagemDatabase::class.java, "postagemDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}