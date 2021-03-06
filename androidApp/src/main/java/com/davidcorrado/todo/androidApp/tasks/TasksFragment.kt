package com.davidcorrado.todo.androidApp.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.davidcorrado.todo.androidApp.R
import com.davidcorrado.todo.shared.feature.tasks.TasksKVM
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            val tasksVM = TasksVM().kvm
            tasksVM.toDoList.onEach {
                val tv: TextView = view.findViewById(R.id.message)
                tv.text = it.firstOrNull()?.title ?: "NULL"
            }.launchIn(this)
            tasksVM.updateList()
        }
    }
}