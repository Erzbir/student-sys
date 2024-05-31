package com.erzbir.student.view;

import android.content.Intent;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.erzbir.student.R;
import com.erzbir.student.activity.StudentDetailActivity;
import com.erzbir.student.adapter.StudentAdapter;
import com.erzbir.student.common.AppActivity;
import com.erzbir.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/30 17:25
 */
public class StudentListActivity extends AppActivity {

    private RecyclerView rv_students;
    private Button b_add;
    private Button b_cancel;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;

    protected void initView() {
        setContentView(R.layout.activity_student_list);
        rv_students = findViewById(R.id.rv_students);
        rv_students.setLayoutManager(new LinearLayoutManager(this));
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList, student -> {
            Intent intent = new Intent(StudentListActivity.this, StudentDetailActivity.class);
            intent.putExtra("student", student);
            startActivity(intent);
        });
        rv_students.setAdapter(studentAdapter);
        b_add = findViewById(R.id.b_add);
        b_cancel = findViewById(R.id.b_cancel);
    }

    protected void initOnClickCallback() {
        setAddOnClick();
        setCancelOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

    }


    private void setAddOnClick() {
        b_add.setOnClickListener(v -> {
            // Save student details logic
        });
    }

    private void setCancelOnClick() {
        b_cancel.setOnClickListener(v -> {
            // Save student details logic
        });
    }
}

