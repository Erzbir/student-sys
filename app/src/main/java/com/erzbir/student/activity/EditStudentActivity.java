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
    private Student editedStudent;
    private Spinner sp_type;
    private EditText et_name;
    private EditText et_money;
    private EditText et_time;
    private EditText et_detail;
    private Button bt_confirm;
    private Button bt_del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initOnClickCallback();
    }

    private void initView() {
        setContentView(R.layout.activity_edit);
        et_name = findViewById(R.id.et_name);
        et_money = findViewById(R.id.et_money);
        et_detail = findViewById(R.id.et_detail);
        et_time = findViewById(R.id.et_time);
        sp_type = findViewById(R.id.sp_type);
        bt_confirm = findViewById(R.id.bt_confirm);
        bt_del = findViewById(R.id.bt_del);
        initFromExtra();
    }

    private void initFromExtra() {
        editedStudent = getIntent().getSerializableExtra("stu", Student.class);
        et_name.setText(editedStudent.getName());
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
        setDeleteOnClick();
        setConfirmOnClick();
    }

    private void setDeleteOnClick() {
        bt_del.setOnClickListener(v -> {
            StudentManageComponent studentManageComponent = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
            studentManageComponent.remove(editedStudent);
            Intent intent = new Intent(EditStudentActivity.this, DetailActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setConfirmOnClick() {
        bt_confirm.setOnClickListener(v -> {
            int position = sp_type.getSelectedItemPosition();
            StudentManageComponent studentManageComponent = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
            editedStudent.setName(et_name.getText().toString());
            studentManageComponent.update(editedStudent);
            Intent intent = new Intent(EditStudentActivity.this, DetailActivity.class);
            startActivity(intent);
            finish();
        });
    }

}
