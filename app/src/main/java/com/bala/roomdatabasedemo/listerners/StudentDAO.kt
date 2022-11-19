package com.bala.studentappdemo.listerners

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bala.studentappdemo.model.Student

@Dao
interface StudentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudent(student: Student) : Long

    @Query("select * from StudentTBL where StudentMobileNumber LIKE :userMobileNumber AND StudentPassword LIKE :userPassword")
    fun performLoginOperation(userMobileNumber : String,userPassword : String) : Student

    @Query("select * from StudentTBL where StudentMobileNumber LIKE :userMobileNumber")
    fun retriveDataFromDB(userMobileNumber : String) : Student


    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateStudent(student: Student)

    @Query("select * from StudentTBL")
    fun getStudentsData() : List<Student>


}