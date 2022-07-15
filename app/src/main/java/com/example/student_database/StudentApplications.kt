package com.example.student_database

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class StudentApplications  : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { StudentDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { StudentRepositry(database.st_Dao()) }
}