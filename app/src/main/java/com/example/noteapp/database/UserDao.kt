package com.example.noteapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userData: UserData)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun realAllData(): LiveData<List<UserData>>

    @Update
    suspend fun updateUser(userData: UserData)

    @Delete
    suspend fun deleteUser(userData: UserData)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table WHERE titletxt LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<UserData>>
}