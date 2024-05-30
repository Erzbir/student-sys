package com.erzbir.student.scan;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;

import java.io.IOException;
import java.util.*;

import static android.content.ContentValues.TAG;

/**
 * @author Erzbir
 * @since 2024/5/29
 */
public class ScanUtil {
    public static Set<Class<?>> scanAllClasses(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        Set<Class<?>> classes = new HashSet<>();
        for (ApplicationInfo packageInfo : packages) {
            try {
                if (!packageInfo.packageName.equals("com.erzbir.student")) {
                    continue;
                }
                String packageName = packageInfo.packageName;
                Log.d(TAG, "Package Name: " + packageName);
                String packagePath = packageInfo.sourceDir;
                DexClassLoader classLoader = new DexClassLoader(packagePath, context.getCacheDir().getAbsolutePath(), null, context.getClass().getClassLoader());
                List<String> classNames = getClassNames(packagePath);
                classNames.stream()
                        .filter(className -> className.startsWith("com.erzbir.student"))
                        .filter(className -> className.substring(className.lastIndexOf(".")).startsWith(".R$"))
                        .forEach(className -> {
                            Log.d(TAG, "Class Name: " + className);
                            try {
                                Class<?> clazz = classLoader.loadClass(className);
                                classes.add(clazz);
                            } catch (ClassNotFoundException e) {
                                Log.e(TAG, "Error loading class: " + className, e);
                            }

                        });
            } catch (Exception e) {
                Log.e(TAG, "Error scanning package: " + packageInfo.packageName, e);
            }
        }
        return classes;
    }

    private static List<String> getClassNames(String apkFilePath) {
        List<String> classNames = new ArrayList<>();
        try {
            DexFile dexFile = new DexFile(apkFilePath);
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()) {
                String className = entries.nextElement();
                classNames.add(className);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading APK file: " + apkFilePath, e);
        }
        return classNames;
    }

}
