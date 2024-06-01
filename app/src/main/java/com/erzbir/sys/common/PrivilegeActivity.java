package com.erzbir.sys.common;

import android.content.Intent;
import android.os.Bundle;
import com.erzbir.sys.util.SavedUser;
import com.erzbir.sys.view.AccessActivity;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/6/1
 */
public abstract class PrivilegeActivity extends AppActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (SavedUser.getUser() == null) {
            Intent intent = new Intent(this, AccessActivity.class);
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
    }
}
