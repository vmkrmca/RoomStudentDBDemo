package com.bala.studentappdemo.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.bala.roomdatabasedemo.R
import com.bala.studentappdemo.database.StudentDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : Activity(), View.OnClickListener {

    lateinit var tvRegister : TextView
    lateinit var tvLogin : TextView
    lateinit var etMobileNumber : EditText
    lateinit var etPassword : EditText

    private val studentDataBase by lazy { StudentDataBase.getDatabase(this).studentDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)

        tvRegister = findViewById(R.id.tvRegister)
        tvRegister.setOnClickListener(this)

        tvLogin = findViewById(R.id.tvLogin)
        tvLogin.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v?.id) {

            R.id.tvRegister ->{

                var intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

            R.id.tvLogin ->{

                var userMobileNumber = etMobileNumber.text.toString()
                var userPassword = etPassword.text.toString()

                GlobalScope.launch(Dispatchers.IO){

                    var isLoggedIN =  studentDataBase.performLoginOperation(userMobileNumber,userPassword)

                    if (isLoggedIN.studentMobileNumber == userMobileNumber) {
                        var intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
                        intent.putExtra("MOBILE_NUMBER",userMobileNumber)
                        startActivity(intent)
                    }

                }
            }
        }
    }
}