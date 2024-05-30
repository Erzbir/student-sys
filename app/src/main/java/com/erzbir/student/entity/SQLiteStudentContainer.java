package com.erzbir.student.entity;

import com.erzbir.student.AndroidApplication;
import com.erzbir.student.dao.StudentDao;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class SQLiteStudentContainer implements StudentContainer {
    private final Map<Long, IStudent> studentMap = new ConcurrentHashMap<>();
    private final StudentDao studentDao = AndroidApplication.INSTANCE.DB.studentDao();

    public SQLiteStudentContainer() {
        studentDao.getAll().forEach(v -> studentMap.put(v.getId(), v));
    }

    @Override
    public void add(IStudent student) {
        if (student.getId() == null || student.getId() == 0) {
            student.setId((long) (studentMap.size() + 1));
        }
        studentMap.put(student.getId(), student);
        CompletableFuture.runAsync(() -> studentDao.insert((Student) student));
    }

    @Override
    public IStudent get(Long key) {
        return studentMap.get(key);
    }

    @Override
    public void remove(Long key) {
        studentMap.remove(key);
        Student student = new Student.Builder().id(key).build();
        CompletableFuture.runAsync(() -> studentDao.delete(student));
    }

    @Override
    public void update(IStudent student) {
        studentMap.put(student.getId(), student);
        CompletableFuture.runAsync(() -> studentDao.update((Student) studentDao));
    }

    @Override
    public boolean contains(IStudent student) {
        return studentMap.containsKey(student.getId());
    }

    @Override
    public Map<Long, IStudent> getItems() {
        return studentMap;
    }
}
