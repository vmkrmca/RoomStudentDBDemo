package com.bala.studentappdemo.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.bala.roomdatabasedemo.R
import com.bala.studentappdemo.database.StudentDataBase
import com.bala.studentappdemo.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashBoardActivity : Activity(), View.OnClickListener {

    lateinit var tvStudentList : TextView
    lateinit var tvUpdate : TextView
    var userMobileNUmber : String = "";

    private val studentDataBase by lazy { StudentDataBase.getDatabase(this).studentDao() }

    lateinit var etUserMobileNumber : TextView
    lateinit var etUserName : EditText
    lateinit var etUserAge : EditText
    lateinit var etUserEmailAddress : EditText
    lateinit var etUserPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        etUserName = findViewById(R.id.etUserName)
        etUserAge = findViewById(R.id.etUserAge)
        etUserPassword = findViewById(R.id.etPassword)
        etUserEmailAddress = findViewById(R.id.etEmailAddress)
        etUserMobileNumber = findViewById(R.id.etMobileNumber)

        userMobileNUmber = intent?.extras?.getString("MOBILE_NUMBER","").toString()
        etUserMobileNumber.text = "$userMobileNUmber"
        tvStudentList = findViewById(R.id.tvStudentList)
        tvUpdate = findViewById(R.id.tvUpdate)
        tvStudentList.setOnClickListener(this)
        tvUpdate.setOnClickListener(this)

        GlobalScope.launch(Dispatchers.IO) {

            readDataFromDB()
        }
    }

    private fun readDataFromDB() {

        var student  = studentDataBase.retriveDataFromDB(userMobileNUmber)
        etUserEmailAddress.setText("${student.studentEmailAddress}")
        etUserAge.setText("${student.studentAge}")
        etUserName.setText("${student.studentName}")
        etUserPassword.setText("${student.studentPassword}")

    }

    override fun onClick(v: View?) {

        when(v?.id) {

            R.id.tvUpdate ->{

                var userName = etUserName.text.toString()
                var userMobileNumber = etUserMobileNumber.text.toString()
                var userAge  = etUserAge.text.toString()
                var userPassword = etUserPassword.text.toString()
                val userEmailAddress = etUserEmailAddress.text.toString()

                    GlobalScope.launch(Dispatchers.IO) {

                        studentDataBase.updateStudent(Student(userName,userMobileNumber,userAge.toInt(),userPassword,userEmailAddress))
                    }

            }

            R.id.tvStudentList ->{

                var intent = Intent(this@DashBoardActivity, StudentsListActivity :: class.java)
                startActivity(intent)
                finish()
            }


        }

    }

}
