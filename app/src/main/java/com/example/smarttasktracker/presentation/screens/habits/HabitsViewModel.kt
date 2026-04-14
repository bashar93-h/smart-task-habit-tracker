package com.example.smarttasktracker.presentation.screens.habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.usecase.habits.HabitUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HabitsViewModel @Inject constructor(private val useCases: HabitUseCases) : ViewModel() {

    private val _habits = MutableStateFlow<List<HabitItem>>(emptyList())
    val habits = _habits.asStateFlow()

    private val _selectedHabit = MutableStateFlow<HabitItem?>(null)
    val selectedHabit = _selectedHabit.asStateFlow()

    init {
        loadHabits()
    }

    fun loadHabits() {
        viewModelScope.launch {
            useCases.getAllHabits().collect { habitList ->
                _habits.value = habitList
            }
        }
    }

    fun getHabitById(habitId: Int) {
        viewModelScope.launch {
            _selectedHabit.value = useCases.getHabitById(habitId)
        }
    }

    fun addHabit(habit: HabitItem) {
        viewModelScope.launch {
            useCases.insertHabit(habit)
        }
    }

    fun deleteHabit(habit: HabitItem) {
        viewModelScope.launch {
            useCases.deleteHabit(habit)
        }
    }

    fun updateHabit(habit: HabitItem) {
        viewModelScope.launch {
            useCases.updateHabit(habit)
        }
    }
}
