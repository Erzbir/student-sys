package com.erzbir.sys.util;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/6/2
 */
public class MethodLocker {
    private static final Set<String> lockedMethods = new ConcurrentSkipListSet<>();

    public static void lock(String key, Runnable runnable) {
        if (lockedMethods.contains(key)) {
            return;
        }
        lockedMethods.add(key);
        runnable.run();
    }
}
