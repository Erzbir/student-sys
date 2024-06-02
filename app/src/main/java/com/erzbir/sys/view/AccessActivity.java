package com.erzbir.sys.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.erzbir.sys.AndroidApplication;
import com.erzbir.sys.R;
import com.erzbir.sys.application.DefaultApplication;
import com.erzbir.sys.common.AppActivity;
import com.erzbir.sys.component.LoginComponent;
import com.erzbir.sys.component.RegisterComponent;
import com.erzbir.sys.entity.User;
import com.erzbir.sys.event.UserLoginEvent;
import com.erzbir.sys.event.UserRegisterEvent;
import com.erzbir.sys.util.SavedUser;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class AccessActivity extends AppActivity {

    private EditText et_username;
    private EditText et_password;
    private Button b_register;
    private Button b_login;
    private Button b_setting;
    private CheckBox cb_remember;

    protected void initOnClickCallback() {
        setLoginOnClick();
        setRegisterOnClick();
        setSettingOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

    }

    protected void initView() {
        setContentView(R.layout.activity_access);
        b_register = findViewById(R.id.b_register);
        b_login = findViewById(R.id.b_login);
        b_setting = findViewById(R.id.b_setting);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        cb_remember = findViewById(R.id.cb_remember);
        remember();
    }

    private void remember() {
        SharedPreferences user = getSharedPreferences("user", 0);
        boolean rem = user.getBoolean(SavedUser.REM_PASSWORD_KEY, false);
        if (rem) {
            try {
                cb_remember.setChecked(true);
                String username = user.getString(SavedUser.USERNAME_KEY, "");
                String password = user.getString(SavedUser.PASSWORD_KEY, "");
                et_username.setText(username);
                et_password.setText(password);
            } catch (Exception e) {
                cb_remember.setChecked(false);
                user.edit().putBoolean(SavedUser.REM_PASSWORD_KEY, false).apply();
            }
        }
    }


    private void setRegisterOnClick() {
        b_register.setOnClickListener(view -> {
            RegisterComponent registerComponent = AndroidApplication.INSTANCE.APP.getComponent(RegisterComponent.class);
            User user = new User.Builder().username(et_username.getText().toString()).password(et_password.getText().toString()).build();
            DefaultApplication.INSTANCE.dispatchEvent(new UserRegisterEvent(user));
            registerComponent.register(user);
        });
    }

    private void setSettingOnClick() {
        b_setting.setOnClickListener(view -> {
            Intent intent = new Intent(AccessActivity.this, com.erzbir.sys.view.SettingActivity.class);
            startActivity(intent);
            finish();
        });
    }


    private void setLoginOnClick() {
        b_login.setOnClickListener(view -> {
            LoginComponent loginComponent = AndroidApplication.INSTANCE.APP.getComponent(LoginComponent.class);
            User user = new User.Builder().username(et_username.getText().toString()).password(et_password.getText().toString()).build();
            DefaultApplication.INSTANCE.dispatchEvent(new UserLoginEvent(user));
            if (loginComponent.login(user)) {
                SharedPreferences preferences = getSharedPreferences("user", 0);
                SharedPreferences.Editor editor = preferences.edit();
                if (cb_remember.isChecked()) {
                    editor.putBoolean(SavedUser.REM_PASSWORD_KEY, true);
                    editor.putString(SavedUser.USERNAME_KEY, user.getUsername());
                    editor.putString(SavedUser.PASSWORD_KEY, user.getPassword());
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
