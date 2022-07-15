package com.example.student_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [StudentData::class], version = 1, exportSchema = false)
abstract class StudentDatabase:RoomDatabase() {
    abstract fun st_Dao():StudentDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): StudentDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_database"
                ).build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.st_Dao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: StudentDao) {
            // Delete all content here.
            wordDao.deleteAllNotes()

            // Add sample words.
            var word = StudentData("Shakila","Maths","Science","Ënglish",1)
            wordDao.insert(word)
            word = StudentData("Shakila","Maths","Science","Ënglish",2)
            wordDao.insert(word)

            // TODO: Add your own words!
        }
    }
}
