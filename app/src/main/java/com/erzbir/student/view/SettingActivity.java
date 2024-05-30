package com.erzbir.student.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.erzbir.student.R;
import com.erzbir.student.activity.AccessChangeActivity;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class SettingActivity extends AppCompatActivity {

    private Button bt_about;
    private Button bt_changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initOnClickCallback();
    }

    private void initView() {
        setContentView(R.layout.activity_setting);
        bt_about = findViewById(R.id.b_about);
        bt_changePassword = findViewById(R.id.b_changePassword);
    }

    private void initOnClickCallback() {
        setAboutOnClick();
        setChangeOnClick();
    }

    private void setChangeOnClick() {
        bt_changePassword.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, AccessChangeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setAboutOnClick() {
        bt_about.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, AboutActivity.class);
            startActivity(intent);
            finish();
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, keyEvent);
    }
}
