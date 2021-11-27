package com.example.msslabfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.msslabfour.database.StudentDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextScore;
    private Button btnAddStudent;
    private Button btnRemoveStudentList;
    private RecyclerView rvStudent;

    private StudentAdapter studentAdapter;
    private List<Student> mListStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        studentAdapter = new StudentAdapter();
        mListStudent = new ArrayList<>();
        studentAdapter.setData(mListStudent);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvStudent.setLayoutManager(linearLayoutManager);
        rvStudent.setAdapter(studentAdapter);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        btnRemoveStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeStudentList();
            }
        });
    }

    private void initUi() {
        editTextName = findViewById(R.id.et_name);
        editTextScore = findViewById(R.id.et_score);
        btnAddStudent = findViewById(R.id.btn_add_student);
        btnRemoveStudentList = findViewById(R.id.btn_remove_studen_list);
        rvStudent = findViewById(R.id.rv_student);
    }

    private void addStudent() {
        String strName = editTextName.getText().toString().trim();
        String strScore = editTextScore.getText().toString().trim();

        if (TextUtils.isEmpty(strName) || TextUtils.isEmpty(strScore)) {
            return;
        }

        Student student = new Student(strName, Double.parseDouble(strScore));
        StudentDatabase.getInstance(this).studentDao().insertStudent(student);
        Toast.makeText(this, "Add student successfully", Toast.LENGTH_SHORT).show();

        editTextName.setText("");
        editTextScore.setText("");
        hideSoftKeyboard();

        mListStudent = StudentDatabase.getInstance(this).studentDao().selectStudentList();
        studentAdapter.setData(mListStudent);
    }
    private void removeStudentList() {
        StudentDatabase.getInstance(this).studentDao().deleteStudentList();
        Toast.makeText(this, "Remove student list successfully", Toast.LENGTH_SHORT).show();

        editTextName.setText("");
        editTextScore.setText("");
        hideSoftKeyboard();

        mListStudent = StudentDatabase.getInstance(this).studentDao().selectStudentList();
        studentAdapter.setData(mListStudent);
    }


    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}