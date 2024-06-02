package com.erzbir.sys;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.StrictMode;
import com.erzbir.sys.application.DefaultApplication;
import com.erzbir.sys.scan.ScanUtil;
import com.erzbir.sys.setting.DefaultSetting;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class AndroidApplication extends Application {
    public static final AndroidApplication INSTANCE = new AndroidApplication();
    public final com.erzbir.sys.application.Application APP = DefaultApplication.INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidApplication.INSTANCE.APP.init(ScanUtil.scanAllClasses(AndroidApplication.this), new DefaultSetting());
        SharedPreferences setting = getSharedPreferences("setting", 0);
        String server = setting.getString("server", "localhost:8080");
        AndroidApplication.INSTANCE.APP.getSetting().setServer(server);
        AndroidApplication.INSTANCE.APP.start();
        StrictMode.ThreadPolicy gfgPolicy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        APP.stop();
    }
}
