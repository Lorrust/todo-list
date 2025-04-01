package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import br.edu.satc.todolistcompose.data.AppDatabase
import br.edu.satc.todolistcompose.data.TaskData
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "br.edu.satc.todolistcompose.TODO_LIST"
        )
            .allowMainThreadQueries()
            .build()

//        val tasks = mutableListOf<TaskData>()
//        for (i in 0..5) {
//            tasks.add(TaskData(0, "Tarefa " + i, "Descricao " + i, i % 2 == 0))
//        }
//
        val taskDao = db.taskDao()
//
//        tasks.forEach {
//            taskDao.insertAll(it)
//        }

        var tasks = taskDao.getAll()

        setContent {
            ToDoListComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(tasks) {
                        taskDao.insertAll(it)
                        tasks = taskDao.getAll()
                    }
                }
            }
        }
    }
}