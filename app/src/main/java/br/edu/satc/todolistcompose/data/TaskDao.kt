package br.edu.satc.todolistcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskData")
    fun getAll(): List<TaskData>

    @Insert
    fun insertAll(vararg tasks: TaskData)

    @Delete
    fun delete(vararg task: TaskData)
}