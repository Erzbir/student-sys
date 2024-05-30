package com.erzbir.student.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.erzbir.student.R;
import com.erzbir.student.entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class MainActivity extends AppCompatActivity {
    private TextView tv_totalCount;
    private LinearLayout ll_majors;
    private Button b_manage;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_totalCount = findViewById(R.id.tv_total);
        ll_majors = findViewById(R.id.ll_majors);
        b_manage = findViewById(R.id.b_manage);

        studentList = new ArrayList<>();
        // 示例数据
        studentList.add(new Student(1L, "Alice", "f", "Computer Science", "Sophomore"));
        studentList.add(new Student(2L, "Bob", "m", "Mechanical Engineering", "Junior"));
        studentList.add(new Student(3L, "Charlie", "m", "Computer Science", "Freshman"));
        studentList.add(new Student(4L, "David", "m", "Electrical Engineering", "Senior"));

        updateStudentInfo();

        b_manage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
            startActivity(intent);
        });
    }

    private void updateStudentInfo() {
        int totalStudents = studentList.size();
        Map<String, Integer> majorCountMap = new HashMap<>();

        for (Student student : studentList) {
            String major = student.getMajor();
            if (majorCountMap.containsKey(major)) {
                majorCountMap.put(major, majorCountMap.get(major) + 1);
            } else {
                majorCountMap.put(major, 1);
            }
        }

        tv_totalCount.setText("Total Students: " + totalStudents);

        ll_majors.removeAllViews();
        for (Map.Entry<String, Integer> entry : majorCountMap.entrySet()) {
            TextView textViewMajor = new TextView(this);
            textViewMajor.setText(entry.getKey() + ": " + entry.getValue() + " students");
            textViewMajor.setTextSize(16);
            textViewMajor.setTextColor(getResources().getColor(R.color.black));
            ll_majors.addView(textViewMajor);
        }
    }
}
