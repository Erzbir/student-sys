package com.erzbir.student.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.erzbir.student.AndroidApplication;
import com.erzbir.student.R;
import com.erzbir.student.common.AppActivity;
import com.erzbir.student.component.UserManageComponent;
import com.erzbir.student.entity.User;
import com.erzbir.student.util.SavedUser;
import com.erzbir.student.view.SettingActivity;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class AccessChangeActivity extends AppActivity {
    private Button b_change;
    private Button b_cancel;
    private TextView et_oldPassword;
    private EditText et_newPassword;
    private EditText et_confirm;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_access);
        b_change = findViewById(R.id.b_changePassword);
        b_cancel = findViewById(R.id.b_cancel);
        et_oldPassword = findViewById(R.id.et_oldPassword);
        et_newPassword = findViewById(R.id.et_newPassword);
        et_confirm = findViewById(R.id.et_confirm);
    }

    protected void initOnClickCallback() {
        setConfirmOnClick();
        setCancelOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

    }

    private void setConfirmOnClick() {
        b_change.setOnClickListener(v -> {
            User user = SavedUser.getUser();
            String oldPassword = et_oldPassword.getText().toString();
            if (!oldPassword.equals(user.getPassword())) {
                Toast.makeText(AccessChangeActivity.this, "旧密码错误", Toast.LENGTH_SHORT).show();
                return;
            }
            String password = et_newPassword.getText().toString();
            String passwordSure = et_confirm.getText().toString();
            if (!passwordSure.equals(password)) {
                Toast.makeText(AccessChangeActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            user.setPassword(password);
            SavedUser.setUser(user);
            AndroidApplication.INSTANCE.APP.getComponent(UserManageComponent.class).update(user);
            Intent intent = new Intent(AccessChangeActivity.this, SettingActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setCancelOnClick() {
        b_cancel.setOnClickListener(v -> {
            Intent intent = new Intent(AccessChangeActivity.this, SettingActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
