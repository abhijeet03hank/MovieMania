package com.hank.moviemania.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hank.moviemania.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object{
//        @Volatile private var INSTANCE: MovieDatabase? = null
//
//        fun getDatabase(context: Context): MovieDatabase {
//            if (INSTANCE == null) {
//                synchronized(this){
//                    INSTANCE = Room.databaseBuilder(context,
//                        MovieDatabase::class.java,
//                        "movieDB")
//                        .build()
//                }
//            }
//            return INSTANCE!!
//        }

        @Volatile
        private var INSTANCE: MovieDatabase? = null
        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build() // <---- The crash occurs here
                INSTANCE = instance
                return instance
            }
        }
    }
}