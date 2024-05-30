package com.erzbir.student.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.erzbir.student.R;
import com.erzbir.student.entity.Student;

/**
 * @author Erzbir
 * @Data: 2024/5/30 17:24
 */
public class StudentDetailActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_name;
    private Spinner sp_gender;
    private EditText et_major;
    private EditText et_grade;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        sp_gender = findViewById(R.id.sp_gender);
        et_major = findViewById(R.id.et_major);
        et_grade = findViewById(R.id.et_grade);

        student = (Student) getIntent().getSerializableExtra("student");
        if (student != null) {
            et_id.setText(String.valueOf(student.getId()));
            et_name.setText(student.getName());
//            sp_gender.setText(student.getGender());
            et_major.setText(student.getMajor());
            et_grade.setText(student.getGrade());
        }

        Button buttonSave = findViewById(R.id.b_save);
        buttonSave.setOnClickListener(v -> {
            // Save student details logic
        });
    }
}
