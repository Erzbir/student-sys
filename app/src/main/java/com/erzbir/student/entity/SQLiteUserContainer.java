package com.erzbir.student.entity;

import com.erzbir.student.AndroidApplication;
import com.erzbir.student.dao.UserDao;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class SQLiteUserContainer implements UserContainer {
    private final Map<String, IUser> userMap = new ConcurrentHashMap<>();
    private final UserDao userDao = AndroidApplication.INSTANCE.DB.userDao();

    public SQLiteUserContainer() {
        userDao.getAll().forEach(v -> userMap.put(v.getUsername(), v));
    }

    @Override
    public void add(IUser user) {
        userMap.putIfAbsent(user.getUsername(), user);
        CompletableFuture.runAsync(() -> userDao.insertAll((User) user));
    }

    @Override
    public IUser get(String key) {
        return userMap.get(key);
    }

    @Override
    public void remove(String key) {
        IUser remove = userMap.remove(key);
        CompletableFuture.runAsync(() -> userDao.delete((User) remove));
    }

    @Override
    public void update(IUser user) {
        userMap.put(user.getUsername(), user);
        CompletableFuture.runAsync(() -> userDao.update((User) user));
    }

    @Override
    public boolean contains(IUser user) {
        return userMap.containsKey(user.getUsername());
    }

    @Override
    public Map<String, IUser> getItems() {
        return userMap;
    }
}
