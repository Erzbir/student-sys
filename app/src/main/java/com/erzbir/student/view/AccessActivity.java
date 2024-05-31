package com.erzbir.student.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.erzbir.student.AndroidApplication;
import com.erzbir.student.R;
import com.erzbir.student.common.AppActivity;
import com.erzbir.student.component.LoginComponent;
import com.erzbir.student.component.RegisterComponent;
import com.erzbir.student.component.UserManageComponent;
import com.erzbir.student.entity.User;
import com.erzbir.student.scan.ScanUtil;
import com.erzbir.student.setting.DefaultSetting;
import com.erzbir.student.util.SavedUser;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class AccessActivity extends AppActivity {

    private EditText et_username;
    private EditText et_password;
    private Button bt_register;
    private Button bt_login;
    private CheckBox cb_remember;

    protected void initOnClickCallback() {
        setLoginOnClick();
        setRegisterOnClick();
    }

    @Override
    protected void initFirst() {
        AndroidApplication.INSTANCE.APP.init(ScanUtil.scanAllClasses(AccessActivity.this), new DefaultSetting());
    }

    @Override
    protected void initLast() {

    }

    protected void initView() {
        setContentView(R.layout.activity_access);
        bt_register = findViewById(R.id.b_register);
        bt_login = findViewById(R.id.b_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        cb_remember = findViewById(R.id.cb_remember);
        SharedPreferences preferences = getSharedPreferences("user", 0);
        boolean rem = preferences.getBoolean(SavedUser.REM_PASSWORD_KEY, false);
        if (rem) {
            cb_remember.setChecked(true);
            String username = preferences.getString(SavedUser.USERNAME_KEY, "");
            String password = AndroidApplication.INSTANCE.APP.getComponent(UserManageComponent.class).getUser(username).getPassword();
            et_username.setText(username);
            et_password.setText(password);
        }
    }


    private void setRegisterOnClick() {
        bt_register.setOnClickListener(view -> {
            RegisterComponent registerComponent = AndroidApplication.INSTANCE.APP.getComponent(RegisterComponent.class);
            User user = new User.Builder().username(et_username.getText().toString()).password(et_password.getText().toString()).build();
            registerComponent.register(user);
        });
    }

    private void setLoginOnClick() {
        bt_login.setOnClickListener(view -> {
            LoginComponent loginComponent = AndroidApplication.INSTANCE.APP.getComponent(LoginComponent.class);
            User user = new User.Builder().username(et_username.getText().toString()).password(et_password.getText().toString()).build();
            if (loginComponent.login(user)) {
                SharedPreferences preferences = getSharedPreferences("user", 0);
                SharedPreferences.Editor editor = preferences.edit();
                if (cb_remember.isChecked()) {
                    editor.putBoolean(SavedUser.REM_PASSWORD_KEY, true);
                    editor.putString(SavedUser.USERNAME_KEY, user.getUsername());
                } else {
                    editor.clear();
                }
                editor.apply();
                SavedUser.setUser(user);
                Toast.makeText(AccessActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AccessActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(AccessActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
