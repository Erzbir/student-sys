package com.erzbir.sys.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.erzbir.sys.R;
import com.erzbir.sys.common.AppActivity;
import com.erzbir.sys.entity.Student;
import com.erzbir.sys.view.MainActivity;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class EditStudentActivity extends AppActivity {
    private Student student;
    private Spinner sp_gender;
    private EditText et_name;
    private EditText et_id;
    private EditText et_grade;
    private EditText et_major;
    private Button b_cancel;
    private Button b_save;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_student_detail);
        et_name = findViewById(R.id.et_name);
        sp_gender = findViewById(R.id.sp_gender);
        et_id = findViewById(R.id.et_id);
        et_grade = findViewById(R.id.et_grade);
        et_major = findViewById(R.id.et_major);
        b_save = findViewById(R.id.b_save);
        b_cancel = findViewById(R.id.b_cancel);
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

    @Override
    protected void initOnClickCallback() {
        setDeleteOnClick();
        setSaveOnClick();
        setCancelOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

    }

    private void setDeleteOnClick() {
//        b_delete.setOnClickListener(v -> {
//            StudentManageComponent studentManageComponent = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
//            studentManageComponent.remove(et_student);
//            Intent intent = new Intent(EditStudentActivity.this, DetailActivity.class);
//            startActivity(intent);
//            finish();
//        });
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
