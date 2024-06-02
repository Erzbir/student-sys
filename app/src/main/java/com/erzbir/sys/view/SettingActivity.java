package com.erzbir.sys.view;

import android.content.Intent;
import android.widget.Button;
import com.erzbir.sys.R;
import com.erzbir.sys.activity.AccessChangeActivity;
import com.erzbir.sys.activity.EditServerActivity;
import com.erzbir.sys.common.AppActivity;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class SettingActivity extends AppActivity {

    private Button b_server;
    private Button b_cancel;
    private Button b_about;
    private Button b_changePassword;

    protected void initView() {
        setContentView(R.layout.activity_setting);
        b_server = findViewById(R.id.b_server);
        b_about = findViewById(R.id.b_about);
        b_changePassword = findViewById(R.id.b_changePassword);
        b_cancel = findViewById(R.id.b_cancel);
    }

    protected void initOnClickCallback() {
        setAboutOnClick();
        setChangeOnClick();
        setServerOnClick();
        setCancelOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

    }

    private void setChangeOnClick() {
        b_changePassword.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, AccessChangeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setAboutOnClick() {
        b_about.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, AboutActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setServerOnClick() {
        b_server.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, EditServerActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setCancelOnClick() {
        b_cancel.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        });
    }
}
