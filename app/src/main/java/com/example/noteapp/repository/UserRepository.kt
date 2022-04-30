package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.noteapp.database.UserDao
import com.example.noteapp.database.UserData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserData>> = userDao.realAllData()

    suspend fun addUser(user: UserData) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: UserData) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: UserData) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): Flow<List<UserData>> {
        return userDao.searchDatabase(searchQuery)
    }
}