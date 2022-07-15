package com.example.student_database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "st_table")
class StudentData(val stuName:String,
                  val subject1:String,
                  val subject2:String,
                  val subject3:String,
@PrimaryKey(autoGenerate = true)var id: Int) {
}