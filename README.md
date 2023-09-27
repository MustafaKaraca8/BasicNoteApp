# My Notes

In this application, you can easily take notes, update them and delete your notes by simply swiping right.

# Technologies

### Android Jetpack 

Android Jetpack provides various utility libraries and tools for Android app developers. Many components of Jetpack have been used in this project

### Kotlin

Kotlin has become the official language for Android app development. The entire application in this project is written in the Kotlin language.

### Room Database

Room provides local SQLite database access for Android applications. In this project, Room is used for storing and managing notes. This database is managed using the Note class and the NoteDao interface.

### View Model

ViewModel is a component in Android applications responsible for storing and managing data independently of the UI. In this project, ViewModel classes like AddNoteViewModel and ShowNotesViewModel have been used. These classes facilitate data storage and processing.

### Live Data 

LiveData provides data-holding and observing functions with lifecycle awareness. In this project, LiveData is used for observing data by the UI and automatically updating it.

### Recycler View 

RecyclerView is an Android component used for organizing and displaying lists and data groups. In this project, RecyclerView is used to list the notes. The RecyclerViewAdapter class provides a customized adapter for displaying the notes.

### Navigation Component 

The Navigation Component is used to simplify navigation processes in Android applications. In this project, it is used for navigation between fragments. The nav_graph.xml file defines the navigation graph of the application.

### Hilt (Dependency Injection)

Hilt is a dependency injection framework that simplifies dependency injection in Android applications. In this project, dependency injection is facilitated using NoteModule and @HiltViewModel.

### DiffUtil

DiffUtil is used to detect data changes within a RecyclerView and optimize data updates. In this project, the DiffUtilSingleton class is used to manage data updates in the RecyclerView.

### Toast

Toast is used in Android applications to display short-lived notifications. In this project, Toast is used to provide feedback to the user during note operations.

### View Binding

View Binding provides direct and safe access to UI components in XML layout files, eliminating the need to use the findViewById() method. View Binding is faster than the findViewById() method and can improve the performance of the application.

### Material Design

Material Design, a modern approach to Android application design, has been utilized in this project. User interface components have been designed in accordance with Material Design guidelines.

# Usage

The application is very simple to use, first let's talk about adding a note.

![noteee2](https://github.com/MustafaKaraca8/BasicNoteApp/assets/101111089/12319bb4-9add-4d45-b215-f1c2019e9702)


This view is where our notes will be displayed and we can go to the place of writing our notes by pressing the button at the bottom right.
The view we will see on the screen we go to will be where we write a title, take our note and add it.

![noteee3](https://github.com/MustafaKaraca8/BasicNoteApp/assets/101111089/7d34b113-5e7e-4c53-9dd9-d7df814e4d4f)


That's it, now you've added a note and you can update it at any time by pressing the added note or delete it by swiping right.

![noteee](https://github.com/MustafaKaraca8/BasicNoteApp/assets/101111089/e599798f-3d19-4b63-aa97-8d6ac5f998ae)

