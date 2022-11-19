package com.bala.studentappdemo.database

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bala.studentappdemo.listerners.StudentDAO
import com.bala.studentappdemo.model.Student

@Database(entities = [Student::class], version = 1)
abstract class StudentDataBase : RoomDatabase() {

    abstract fun studentDao() : StudentDAO

    companion object {

        @Volatile
        private var INSTANCE: StudentDataBase? = null

        fun getDatabase(context: Context): StudentDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDataBase::class.java,
                    "food_item_database"
                )
                    .build()

                INSTANCE = instance

                // return instance
                instance
            }
        }
    }
}