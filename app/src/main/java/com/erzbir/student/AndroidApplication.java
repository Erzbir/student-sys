package com.erzbir.student;

import android.app.Application;
import com.erzbir.student.application.DefaultApplication;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class AndroidApplication extends Application {
    public static final AndroidApplication INSTANCE = new AndroidApplication();
    public final com.erzbir.student.application.Application APP = DefaultApplication.INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        APP.stop();
    }
}
