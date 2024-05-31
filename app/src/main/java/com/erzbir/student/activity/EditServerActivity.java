package com.erzbir.student.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.erzbir.student.R;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public class EditServerActivity extends AppCompatActivity {
    private EditText et_server;
    private TextView tv_server;
    private Button b_save;
    private Button b_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initOnClickCallback();
    }

    private void initView() {
        setContentView(R.layout.activity_edit_server);
        b_save = findViewById(R.id.b_save);
        b_cancel = findViewById(R.id.b_cancel);
        et_server = findViewById(R.id.et_server);
        tv_server = findViewById(R.id.tv_server);
    }

    private void initOnClickCallback() {
    }

}
