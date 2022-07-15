package com.example.student_database

import androidx.annotation.WorkerThread
import java.util.concurrent.Flow

class StudentRepositry(private  val studentDao: StudentDao) {

    val allDetails =studentDao.getAllDetails()


@Suppress("RedundantSuspendModifier")
@WorkerThread
suspend fun insert(st_data:StudentData){
    studentDao.insert(st_data)
}

}