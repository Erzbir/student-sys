package com.erzbir.sys.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.erzbir.sys.R;
import com.erzbir.sys.common.AppActivity;
import com.erzbir.sys.entity.Student;

/**
 * @author Erzbir
 * @Data: 2024/5/30 17:24
 */
public class StudentDetailActivity extends AppActivity {

    private EditText et_id;
    private EditText et_name;
    private Spinner sp_gender;
    private EditText et_major;
    private EditText et_grade;
    private Button b_save;
    private Button b_cancel;
    private Student student;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_student_detail);
        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        sp_gender = findViewById(R.id.sp_gender);
        et_major = findViewById(R.id.et_major);
        et_grade = findViewById(R.id.et_grade);
        b_save = findViewById(R.id.b_save);
        b_cancel = findViewById(R.id.b_cancel);
        student = (Student) getIntent().getSerializableExtra("student");
        if (student != null) {
            et_id.setText(String.valueOf(student.getId()));
            et_name.setText(student.getName());
//            sp_gender.setText(student.getGender());
            et_major.setText(student.getMajor());
            et_grade.setText(student.getGrade());
        }
    }

    @Override
    protected void initOnClickCallback() {
        setSaveOnClick();
        setCancelOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

    }

    private void setSaveOnClick() {
        b_save.setOnClickListener(v -> {
            // Save student details logic
        });
    }

    private void setCancelOnClick() {
        b_cancel.setOnClickListener(v -> {
            // Save student details logic
        });
    }
}
