package com.erzbir.sys.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.appcompat.app.AppCompatActivity;
import com.erzbir.sys.view.MainActivity;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public abstract class AppActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFirst();
        initView();
        initOnClickCallback();
        initLast();
    }

    protected abstract void initView();

    protected abstract void initOnClickCallback();

    protected abstract void initFirst();

    protected abstract void initLast();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, keyEvent);
    }
}
