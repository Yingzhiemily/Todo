package com.example.todo.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.todo.model.Todo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.TodosApi
import kotlinx.coroutines.launch

class TodoViewModel:ViewModel() {
    var todos = mutableListOf<Todo>()
        private set

    init {
        getTodosList()
    }

    private fun getTodosList() {
        viewModelScope.launch {
            var todosApi: TodosApi? = null
            try {
                todosApi = TodosApi!!.getInstance()
                todos.clear()
                todos.addAll(todosApi.getTodos())
            } catch (e: Exception) {
                Log.d("TODOVIEWMODEL",e.message.toString())
            }
        }
    }
}