package com.example.smarttasktracker.presentation.utils

fun String.turncate(max: Int): String {
    return if (length > max) {
        take(max).trimEnd() + "..."
    } else {
        this
    }
}