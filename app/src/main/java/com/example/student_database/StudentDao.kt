package com.example.student_database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    fun  insert(studentData: StudentData)

    @Update
    fun update(studentData: StudentData)

    @Delete
    fun  delete(studentData: StudentData)

    @Query("delete from st_table ")
    fun  deleteAllNotes()

    @Query("Select * from st_table order by id")
    fun  getAllDetails():LiveData<List<StudentData>>
}