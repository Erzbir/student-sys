package com.erzbir.student.common;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

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
}
