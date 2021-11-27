package com.example.msslabfour.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.msslabfour.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insertStudent(Student student);

    @Query("SELECT * FROM student")
    List<Student> selectStudentList();

    @Query("DELETE FROM student")
    void deleteStudentList();
}
