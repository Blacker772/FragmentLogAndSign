package com.example.fragmentlogandsign.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fragmentlogandsign.database.Person

@Dao
interface DAO {

    @Insert
    fun insertPerson(item: Person)

    @Query("SELECT * FROM table_for_account WHERE login = :login")
    fun qetUserByLogin(login: String): Person
}