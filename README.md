# SmartTracker App

**SmartTracker** is an Android app built with **Kotlin**, following **Clean Architecture** and **MVVM**.  
It helps users manage **tasks, habits, and motivation**, with notifications and reminders.

## Features
- Task management (add/edit/delete, priority, due date, reminders)
- Habit tracker (daily habits, streaks, reminders)
- Motivation quotes screen
- Clean Architecture with Domain, Data, Presentation layers
- Jetpack Compose UI
- Navigation Component
- Retrofit & Coil for network & image loading
- Room Database for local storage
- WorkManager for reminders
- Hilt for dependency injection

## Screens
- Home
- Tasks
- Add/Edit Task
- Habits
- Add Habit
- Motivation
- Settings

## Project Setup
1. Clone the repo
2. Open in Android Studio
3. Sync Gradle to download dependencies
4. Run on an emulator or device

## Architecture
- **Presentation:** Compose UI, ViewModels, navigation
- **Domain:** Models, repository interfaces, use cases
- **Data:** Repositories, DataSources, Room, Retrofit

## License
MIT License