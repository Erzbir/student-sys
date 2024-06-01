package com.erzbir.sys.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.erzbir.sys.AndroidApplication;
import com.erzbir.sys.R;
import com.erzbir.sys.common.AppActivity;
import com.erzbir.sys.view.MainActivity;
import com.erzbir.sys.view.SettingActivity;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public class EditServerActivity extends AppActivity {
    private EditText et_server;
    private TextView tv_server;
    private Button b_save;
    private Button b_cancel;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_edit_server);
        b_save = findViewById(R.id.b_save);
        b_cancel = findViewById(R.id.b_cancel);
        et_server = findViewById(R.id.et_server);
        tv_server = findViewById(R.id.tv_server);
        SharedPreferences preferences = getSharedPreferences("setting", 0);
        String server = preferences.getString("server", "localhost:8080");
        tv_server.setText(server);
        AndroidApplication.INSTANCE.APP.getSetting().setServer(server);
    }

    @Override
    protected void initOnClickCallback() {
        setSaveOnclick();
        setCancelOnClick();
    }

    private void setSaveOnclick() {
        b_save.setOnClickListener(view -> {

            SharedPreferences preferences = getSharedPreferences("setting", 0);
            SharedPreferences.Editor editor = preferences.edit();
            String server = et_server.getText().toString();
            tv_server.setText(server);
            editor.putString("server", server);
            editor.apply();
            AndroidApplication.INSTANCE.APP.getSetting().setServer(server);
            Intent intent = new Intent(EditServerActivity.this, SettingActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setCancelOnClick() {
        b_cancel.setOnClickListener(v -> {
            Intent intent = new Intent(EditServerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

    }


}
