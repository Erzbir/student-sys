package com.erzbir.student.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.erzbir.student.R;
import com.erzbir.student.activity.StudentDetailActivity;
import com.erzbir.student.adapter.StudentAdapter;
import com.erzbir.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/30 17:25
 */
public class StudentListActivity extends AppCompatActivity {

    private RecyclerView rv_students;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        rv_students = findViewById(R.id.rv_students);
        rv_students.setLayoutManager(new LinearLayoutManager(this));

        studentList = new ArrayList<>();
        // Add your student data to studentList

        studentAdapter = new StudentAdapter(studentList, student -> {
            Intent intent = new Intent(StudentListActivity.this, StudentDetailActivity.class);
            intent.putExtra("student", student);
            startActivity(intent);
        });

        rv_students.setAdapter(studentAdapter);

        Button buttonAddStudent = findViewById(R.id.b_add);
        buttonAddStudent.setOnClickListener(v -> {
            // Add new student logic
        });
    }
}

