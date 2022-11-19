package com.bala.studentappdemo.activities

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bala.roomdatabasedemo.R
import com.bala.studentappdemo.adapters.StudentListAdapter
import com.bala.studentappdemo.database.StudentDataBase
import com.bala.studentappdemo.listerners.StudentOnItemClickListener
import com.bala.studentappdemo.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentsListActivity : Activity(), StudentOnItemClickListener {

    lateinit var rvStudentsList : RecyclerView
    var studentList  = emptyList<Student>()

    private val studentDataBase by lazy { StudentDataBase.getDatabase(this).studentDao() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)
        rvStudentsList = findViewById(R.id.rvStudentsList)

        var layoutManager  = LinearLayoutManager(this@StudentsListActivity)
        rvStudentsList.layoutManager = layoutManager
        rvStudentsList.setHasFixedSize(true)
        studentList = getDataFromDB()

    }

    private fun getDataFromDB(): List<Student> {

        GlobalScope.launch(Dispatchers.IO) {
            studentList = studentDataBase.getStudentsData();
            var studentListAdapter = StudentListAdapter(this@StudentsListActivity,studentList,this@StudentsListActivity)
            rvStudentsList.adapter = studentListAdapter

        }
        return studentList
    }

    override fun onStudentClick(student: Student) {

        var intent = Intent(this@StudentsListActivity,DashBoardActivity :: class.java)
        intent.putExtra("MOBILE_NUMBER",student.studentMobileNumber)
        startActivity(intent)
        finish()
    }

    override fun onStudentDeleteClick(student: Student) {

        var alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle("Delete Record")
        alertDialog.setMessage("Do you want to Delete ${student.studentMobileNumber}  Record")
        alertDialog.setIcon(android.R.drawable.ic_menu_delete)
        alertDialog.setPositiveButton("YES",DialogInterface.OnClickListener { dialog, which ->

            dialog.dismiss()

        })
        alertDialog.setNegativeButton("NO",DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        alertDialog.show()
    }
}
