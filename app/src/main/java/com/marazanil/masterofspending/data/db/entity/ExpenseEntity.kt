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
const val EXPENSE_TABLE_NAME = "expenses"

@Parcelize
@Entity(tableName = EXPENSE_TABLE_NAME)

data class ExpenseEntity(
    @ColumnInfo(name = "expenseName") var expenseName: String,
    @ColumnInfo(name = "expenseDetails") var expenseDetails: String?,
    @ColumnInfo(name = "expenseNumber") var expenseNumber: Int,
    @ColumnInfo(name = "expensePrice") var expensePrice: String,
    @ColumnInfo(name = "currencyType") var currencyType: String,
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}