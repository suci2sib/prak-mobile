package com.example.suciapps.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.suciapps.data.dao.NoteDao
import com.example.suciapps.data.dao.TodoDao
import com.example.suciapps.data.entity.NoteEntity
import com.example.suciapps.data.entity.TodoEntity

// Di sini kita tambahkan TodoEntity dan naikkan versi menjadi 2
@Database(entities = [NoteEntity::class, TodoEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun todoDao(): TodoDao // Tambahkan DAO untuk Todo

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "suci_database"
                )
                    .fallbackToDestructiveMigration() // Biar aman saat naik versi database
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}