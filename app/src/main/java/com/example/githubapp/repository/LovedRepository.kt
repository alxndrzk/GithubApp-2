package com.example.githubapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubapp.database.Loved
import com.example.githubapp.database.LovedDao
import com.example.githubapp.database.LovedRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LovedRepository(application: Application) {
    private val userLovedDao: LovedDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = LovedRoomDatabase.getDatabase(application)
        userLovedDao = db.lovedDao()
    }

    fun getAllLoved(): LiveData<List<Loved>> = userLovedDao.getAllLoved()

    fun getAllLovedByName(username: String) : LiveData<Loved> = userLovedDao.getAllLovedByName(username)


    fun insert(loved: Loved) {
        executorService.execute { userLovedDao.insert(loved) }
    }
    fun delete(loved: Loved) {
        executorService.execute { userLovedDao.delete(loved) }
    }
    fun update(loved: Loved) {
        executorService.execute { userLovedDao.update(loved) }
    }

}