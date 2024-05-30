package com.erzbir.student.dao;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.erzbir.student.entity.Student;
import com.erzbir.student.entity.User;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Database(entities = {User.class, Student.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class, "test").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();

    public abstract StudentDao studentDao();
}
