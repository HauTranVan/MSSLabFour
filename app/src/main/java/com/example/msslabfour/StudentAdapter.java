package com.example.msslabfour;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Cursor mListStudent;

    public void setData(Cursor mListStudent) {
        this.mListStudent = mListStudent;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        if (mListStudent.moveToPosition(position)) {
            holder.tvStudentname.setText(mListStudent.getString(mListStudent.getColumnIndexOrThrow(Student.COLUMN_NAME)));
            holder.tvStudentscore.setText(Double.toString(mListStudent.getDouble(mListStudent.getColumnIndexOrThrow(Student.COLUMN_SCORE))));
        }
    }

    @Override
    public int getItemCount() {
        if (mListStudent != null) {
            return mListStudent.getCount();
        }
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView tvStudentname;
        private TextView tvStudentscore;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentname = itemView.findViewById(R.id.tv_name);
            tvStudentscore = itemView.findViewById(R.id.tv_score);
        }
    }
}