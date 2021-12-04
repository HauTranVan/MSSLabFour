package com.example.msslabfour.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.msslabfour.Student;

@Dao
public interface StudentDao {

    @Insert
    long insertStudent(Student student);

    @Query("SELECT * FROM " + Student.TABLE_NAME)
    Cursor selectStudentList();

    @Query("DELETE FROM " + Student.TABLE_NAME)
    void deleteStudentList();
}
