package com.erzbir.sys.view;

import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.erzbir.sys.R;
import com.erzbir.sys.common.AppActivity;
import com.erzbir.sys.common.PrivilegeActivity;
import com.erzbir.sys.entity.Student;
import com.erzbir.sys.util.SavedUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class MainActivity extends PrivilegeActivity {
    private TextView tv_totalCount;
    private LinearLayout ll_majors;
    private Button b_manage;
    private List<Student> studentList;

    protected void initOnClickCallback() {
        setManageOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {
        updateStudentInfo();
    }

    private void setManageOnClick() {
        b_manage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
            startActivity(intent);
        });
    }

    protected void initView() {
        setContentView(R.layout.activity_main);

        tv_totalCount = findViewById(R.id.tv_total);
        ll_majors = findViewById(R.id.ll_majors);
        b_manage = findViewById(R.id.b_manage);

        studentList = new ArrayList<>();
        studentList.add(new Student(1L, "Alice", "f", "Computer Science", "Sophomore"));
        studentList.add(new Student(2L, "Bob", "m", "Mechanical Engineering", "Junior"));
        studentList.add(new Student(3L, "Charlie", "m", "Computer Science", "Freshman"));
        studentList.add(new Student(4L, "David", "m", "Electrical Engineering", "Senior"));
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
