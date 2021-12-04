package com.example.msslabfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.msslabfour.provider.StudentContentProvider;

public class MainActivity extends AppCompatActivity {

    private Button btnAddStudent;
    private Button btnRemoveStudentList;
    private RecyclerView rvStudent;

    private Cursor mListStudent;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvStudent.setLayoutManager(linearLayoutManager);

        mListStudent = getContentResolver().query(StudentContentProvider.URI_STUDENT, null, null, null);
        studentAdapter = new StudentAdapter();
        studentAdapter.setData(mListStudent);
        rvStudent.setAdapter(studentAdapter);

        btnAddStudent.setOnClickListener(v -> showStudentList());

        btnRemoveStudentList.setOnClickListener(v -> removeStudentList());
    }

    private void initUi() {
        btnAddStudent = findViewById(R.id.btn_show_student_list);
        btnRemoveStudentList = findViewById(R.id.btn_remove_student_list);
        rvStudent = findViewById(R.id.rv_student);
    }

    private void showStudentList() {
        mListStudent = getContentResolver().query(StudentContentProvider.URI_STUDENT, null, null, null);
        studentAdapter.setData(mListStudent);
    }
    private void removeStudentList() {
        if (mListStudent.moveToFirst()) {
            getContentResolver().delete(StudentContentProvider.URI_STUDENT, null, null);
            Toast.makeText(this, "Student list is removed!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Student list is empty!", Toast.LENGTH_SHORT).show();
        }

        mListStudent = getContentResolver().query(StudentContentProvider.URI_STUDENT, null, null, null);
        studentAdapter.setData(mListStudent);
    }
}