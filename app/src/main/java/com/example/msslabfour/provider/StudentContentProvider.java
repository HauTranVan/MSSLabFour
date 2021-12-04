package com.example.msslabfour.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.msslabfour.Student;
import com.example.msslabfour.database.StudentDao;
import com.example.msslabfour.database.StudentDatabase;

public class StudentContentProvider extends ContentProvider {

    private StudentDao studentDao;

    public static final String AUTHORITY = "com.example.msslabfour.provider";
    public static final Uri URI_STUDENT = Uri.parse("content://" + AUTHORITY + "/" + Student.TABLE_NAME);
    public static final int CODE_STUDENT_DIR = 1;
    public static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, Student.TABLE_NAME, CODE_STUDENT_DIR);
    }

    @Override
    public boolean onCreate() {
        studentDao = StudentDatabase.getInstance(getContext()).studentDao();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        if (MATCHER.match(uri) == CODE_STUDENT_DIR) {
            final Cursor cursor;
            cursor = studentDao.selectStudentList();
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }
        else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if (MATCHER.match(uri) == CODE_STUDENT_DIR) {
            final long id = StudentDatabase.getInstance(getContext()).studentDao().insertStudent(Student.fromContentValues(contentValues));
            getContext().getContentResolver().notifyChange(uri, null);
            return ContentUris.withAppendedId(uri, id);
        }
        else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        if (MATCHER.match(uri) == CODE_STUDENT_DIR) {
            StudentDatabase.getInstance(getContext()).studentDao().deleteStudentList();
            getContext().getContentResolver().notifyChange(uri, null);
            return 0;
        }
        else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
