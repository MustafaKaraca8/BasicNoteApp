package com.mustafa.basicnoteapp.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.mustafa.basicnoteapp.roomdb.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Singleton
    @Provides
    fun injectRoomDatabase
                (@ApplicationContext context : Context)
    = Room.databaseBuilder(context,NoteDatabase::class.java,"NoteDataB")
        .build()

    @Singleton
    @Provides
    fun injcetDao(database: NoteDatabase) = database.noteDao()
}