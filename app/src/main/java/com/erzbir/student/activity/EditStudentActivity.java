package com.erzbir.student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.erzbir.student.AndroidApplication;
import com.erzbir.student.R;
import com.erzbir.student.component.StudentManageComponent;
import com.erzbir.student.entity.Student;
import com.erzbir.student.view.DetailActivity;
import com.erzbir.student.view.MainActivity;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class EditStudentActivity extends AppCompatActivity {
    private Student student;
    private Spinner sp_gender;
    private EditText et_name;
    private EditText et_id;
    private EditText et_grade;
    private EditText et_major;
    private Button b_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initOnClickCallback();
    }

    private void initView() {
        setContentView(R.layout.activity_student_detail);
        et_name = findViewById(R.id.et_name);
        sp_gender = findViewById(R.id.sp_gender);
        et_id = findViewById(R.id.et_id);
        et_grade = findViewById(R.id.et_grade);
        et_major = findViewById(R.id.et_major);
        b_save = findViewById(R.id.b_save);
        initFromExtra();
    }

    private void initFromExtra() {
        student = getIntent().getSerializableExtra("stu", Student.class);
        et_name.setText(student.getName());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
            Intent intent = new Intent(EditStudentActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

    private void initOnClickCallback() {
//        setDeleteOnClick();
        setConfirmOnClick();
    }

//    private void setDeleteOnClick() {
//        b_delete.setOnClickListener(v -> {
//            StudentManageComponent studentManageComponent = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
//            studentManageComponent.remove(et_student);
//            Intent intent = new Intent(EditStudentActivity.this, DetailActivity.class);
//            startActivity(intent);
//            finish();
//        });
//    }

    private void setConfirmOnClick() {
        b_save.setOnClickListener(v -> {
            StudentManageComponent studentManageComponent = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
            student.setName(et_name.getText().toString());
            studentManageComponent.update(student);
            Intent intent = new Intent(EditStudentActivity.this, DetailActivity.class);
            startActivity(intent);
            finish();
        });
    }

}
