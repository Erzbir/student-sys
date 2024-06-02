package com.erzbir.sys.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.erzbir.sys.AndroidApplication;
import com.erzbir.sys.R;
import com.erzbir.sys.application.DefaultApplication;
import com.erzbir.sys.common.PrivilegeActivity;
import com.erzbir.sys.component.StudentManageComponent;
import com.erzbir.sys.entity.Student;
import com.erzbir.sys.event.StudentAddEvent;
import com.erzbir.sys.event.StudentUpdateEvent;
import com.erzbir.sys.view.StudentManageActivity;

/**
 * @author Erzbir
 * @Data: 2024/5/30 17:24
 */
public class StudentDetailActivity extends PrivilegeActivity {

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
            sp_gender.setSelection(student.getGender().equals("m") ? 0 : 1);
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
            StudentManageComponent component = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
            int i = getIntent().getIntExtra("add", 0);
            Student newStudent = new Student.Builder()
                    .name(et_name.getText().toString())
                    .gender(sp_gender.getSelectedItem().toString())
                    .major(et_major.getText().toString())
                    .grade(et_grade.getText().toString())
                    .build();
            if (i == 1) {
                DefaultApplication.INSTANCE.dispatchEvent(new StudentAddEvent(newStudent));
                newStudent.setId(Long.parseLong(et_id.getText().toString()));
                component.add(newStudent);
            } else {
                DefaultApplication.INSTANCE.dispatchEvent(new StudentUpdateEvent(newStudent));
                newStudent.setId(student.getId());
                component.update(newStudent);
            }
            Intent intent = new Intent(StudentDetailActivity.this, StudentManageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        });
    }

    private void setCancelOnClick() {
        b_cancel.setOnClickListener(v -> {
            Intent intent = new Intent(StudentDetailActivity.this, StudentManageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        });

    }
}
