package com.bala.studentappdemo.listerners

import com.bala.studentappdemo.model.Student

interface StudentOnItemClickListener {

    fun onStudentClick(name : Student)

    fun onStudentDeleteClick(student: Student)
}