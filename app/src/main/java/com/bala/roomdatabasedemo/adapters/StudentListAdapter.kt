package com.bala.studentappdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bala.roomdatabasedemo.R
import com.bala.studentappdemo.listerners.StudentOnItemClickListener
import com.bala.studentappdemo.model.Student

class StudentListAdapter(private val context: Context, private val studentList : List<Student>,private val studentOnItemClickListener: StudentOnItemClickListener) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    class StudentViewHolder(view : View) : ViewHolder(view) {

        var tvUserMobileNumber: TextView = view.findViewById(R.id.tvUserMobileNumber)
        var tvUserName : TextView= view.findViewById(R.id.tvUserName)
        var tvUserEmail : TextView= view.findViewById(R.id.tvUserEmail)
        var tvUserAge : TextView= view.findViewById(R.id.tvUserAge)
        var ivEditAction : ImageView = view.findViewById(R.id.ivEditAction)
        var ivDeleteAction : ImageView = view.findViewById(R.id.ivDeleteAction)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.student_row,parent,false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.tvUserMobileNumber.text = studentList[position].studentMobileNumber
        holder.tvUserEmail.text = studentList[position].studentEmailAddress
        holder.tvUserName.text = studentList[position].studentName
        holder.tvUserAge.text = "${studentList[position].studentAge}"

        holder.ivEditAction.setOnClickListener {
            studentOnItemClickListener.onStudentClick(studentList[position])
        }

        holder.ivDeleteAction.setOnClickListener {
            studentOnItemClickListener.onStudentDeleteClick(studentList[position])
        }

    }

    override fun getItemCount() = studentList.size
}