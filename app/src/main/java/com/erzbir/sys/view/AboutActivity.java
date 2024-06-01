package com.erzbir.sys.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.erzbir.sys.R;
import com.erzbir.sys.common.AppActivity;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class AboutActivity extends AppActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initOnClickCallback() {

    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, keyEvent);
    }
}
