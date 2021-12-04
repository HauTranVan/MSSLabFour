package com.example.msslabfour;

import android.content.ContentValues;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Student.TABLE_NAME)
public class Student {

    public static final String TABLE_NAME = "student";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = COLUMN_NAME)
    private String name;

    @ColumnInfo(name = COLUMN_SCORE)
    private double score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @NonNull
    public static Student fromContentValues(@Nullable ContentValues values) {
        final Student student = new Student();

        if ((values != null) && values.containsKey(COLUMN_ID)) {
            student.id = values.getAsLong(COLUMN_ID);
        }
        if ((values != null) && values.containsKey(COLUMN_NAME)) {
            student.name = values.getAsString(COLUMN_NAME);
        }
        if ((values != null) && values.containsKey(COLUMN_SCORE)) {
            student.score = values.getAsDouble(COLUMN_SCORE);
        }

        return student;
    }
}
