package com.marazanil.masterofspending.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * Room Databasede tutulacak veriler burada tanımlanmıştır
 *
 * Parcelable nedir? -> Recyclerda card'a veya item 'a tıkladığımızda o veri bir sınıf olarak gönderilir
 * sınıf olarak göndermek içinde Parcelable sınıfından inherit edilmesi gereklidir yoksa sınıf olarak veri taşınamaz!
 *
 */

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expenseId") var expenseId: Long,
    @ColumnInfo(name = "expenseName") var expenseName: String,
    @ColumnInfo(name = "expenseDetails") var expenseDetails: String?,
    @ColumnInfo(name = "expenseNumber") var expenseNumber: Long? = null,
    @ColumnInfo(name = "expensePrice") var expensePrice: String,
    @ColumnInfo(name = "currencyType") var currencyType: String,
)