package com.bala.studentappdemo.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bala.roomdatabasedemo.R
import com.bala.studentappdemo.database.StudentDataBase
import com.bala.studentappdemo.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : Activity(), View.OnClickListener {

    lateinit var etUserMobileNumber : EditText
    lateinit var etUserName : EditText
    lateinit var etUserAge : EditText
    lateinit var etUserEmailAddress : EditText
    lateinit var etUserPassword : EditText
    lateinit var etUserConfirmPassword : EditText
    lateinit var tvRegister : TextView

    private val studentDataBase by lazy { StudentDataBase.getDatabase(this).studentDao() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUserName = findViewById(R.id.etUserName)
        etUserAge = findViewById(R.id.etUserAge)
        etUserPassword = findViewById(R.id.etPassword)
        etUserConfirmPassword = findViewById(R.id.etCPassword)
        etUserEmailAddress = findViewById(R.id.etEmailAddress)
        etUserMobileNumber = findViewById(R.id.etMobileNumber)

        tvRegister = findViewById(R.id.tvRegister)
        tvRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        var userName = etUserName.text.toString()
        var userMobileNumber = etUserMobileNumber.text.toString()
        var userAge  = etUserAge.text.toString()
        var userPassword = etUserPassword.text.toString()
        var userCPassword = etUserConfirmPassword.text.toString()
        val userEmailAddress = etUserEmailAddress.text.toString()

        if (userPassword == userCPassword) {

            GlobalScope.launch(Dispatchers.IO) {
                var id = studentDataBase.insertStudent(Student(userName,userMobileNumber,userAge.toInt(),userPassword,userEmailAddress))

                if (id.toInt()!=-1) {
                    Log.i("IDFromROOMDB","$id")
                    var intent = Intent(this@RegisterActivity,LoginActivity :: class.java)
                    startActivity(intent)
                }
            }

        }
        var intent = Intent(this@RegisterActivity, LoginActivity :: class.java)
        startActivity(intent)

    }

}
