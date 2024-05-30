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
import com.erzbir.student.view.MainActivity;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class AddStudentActivity extends AppCompatActivity {
    private Spinner sp_type;
    private EditText et_name;
    private EditText et_money;
    private EditText et_detail;
    private EditText et_time;
    private Button bt_confirm;
    private Button bt_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initOnClickCallback();
    }

    private void initView() {
        setContentView(R.layout.activity_add);
        et_name = findViewById(R.id.et_name);
        et_money = findViewById(R.id.et_money);
        et_detail = findViewById(R.id.et_detail);
        et_time = findViewById(R.id.et_time);
        sp_type = findViewById(R.id.sp_type);
        bt_confirm = findViewById(R.id.bt_confirm);
        bt_cancel = findViewById(R.id.bt_cancel);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
            Intent intent = new Intent(AddStudentActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

    private void initOnClickCallback() {
        setCancelOnClick();
        setConfirmOnClick();
    }

    private void setCancelOnClick() {
        bt_cancel.setOnClickListener(v -> {
            Intent intent = new Intent(AddStudentActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        });
    }

    private void setConfirmOnClick() {
        bt_confirm.setOnClickListener(v -> {
            int position = sp_type.getSelectedItemPosition();
            StudentManageComponent stuManagerComponent = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
            Student build = new Student.Builder()
                    .name(et_name.getText().toString())
                    .build();
            stuManagerComponent.add(build);
            Intent intent = new Intent(AddStudentActivity.this, AddStudentActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
