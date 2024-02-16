package com.marazanil.masterofspending.di

import android.content.Context
import androidx.room.Room
import com.marazanil.masterofspending.data.db.ExpenseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ExpenseDatabase::class.java, "expenses"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ExpenseDatabase) = database.expenseDao()

}