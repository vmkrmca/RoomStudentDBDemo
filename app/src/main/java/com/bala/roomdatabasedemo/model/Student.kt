package com.bala.studentappdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StudentTBL")
data class Student(
    @ColumnInfo(name = "StudentName") val studentName : String,
    @PrimaryKey  @ColumnInfo(name = "StudentMobileNumber") val studentMobileNumber : String,
    @ColumnInfo(name = "StudentAge") val studentAge : Int,
    @ColumnInfo(name = "StudentPassword") val studentPassword : String,
    @ColumnInfo(name = "StudentEmailAddress") val studentEmailAddress : String)
