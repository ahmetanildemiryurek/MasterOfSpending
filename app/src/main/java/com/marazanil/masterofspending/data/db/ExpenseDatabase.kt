package com.marazanil.masterofspending.data.db

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import com.marazanil.masterofspending.data.db.service.ExpenseDao

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    companion object {
        @Volatile
        private var instance: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ExpenseDatabase::class.java,
                    "EXPENSES DATABASE"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}

