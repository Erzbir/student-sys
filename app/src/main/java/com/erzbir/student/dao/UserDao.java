package com.erzbir.student.dao;

import androidx.room.*;
import com.erzbir.student.entity.User;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE username = :username")
    User findByName(String username);

    @Update
    void update(User user);

    @Insert
    void insertAll(User... users);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
