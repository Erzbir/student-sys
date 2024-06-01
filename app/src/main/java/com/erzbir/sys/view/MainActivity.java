package com.erzbir.sys.view;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.erzbir.event.GlobalEventChannel;
import com.erzbir.event.StandardListenerResult;
import com.erzbir.sys.AndroidApplication;
import com.erzbir.sys.R;
import com.erzbir.sys.adapter.MajorAdapter;
import com.erzbir.sys.common.PrivilegeActivity;
import com.erzbir.sys.component.StudentManageComponent;
import com.erzbir.sys.entity.Student;
import com.erzbir.sys.event.StudentAddEvent;
import com.erzbir.sys.event.StudentDeleteEvent;
import com.erzbir.sys.event.StudentEvent;
import com.erzbir.sys.event.StudentUpdateEvent;

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
    private RecyclerView ll_majors;
    private Button b_manage;
    private List<Student> studentList = new ArrayList<>();

    protected void initOnClickCallback() {
        setManageOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {
        updateStudentInfo();
        registerListener();
    }

    private void registerListener() {
        GlobalEventChannel.INSTANCE.subscribe(StudentEvent.class, event -> {
            Student source = event.getSource();
            Class<? extends StudentEvent> eventClass = event.getClass();
            if (StudentAddEvent.class.equals(eventClass) || StudentUpdateEvent.class.equals(eventClass)) {
                studentList.add(source);
            } else if (StudentDeleteEvent.class.equals(eventClass)) {
                studentList.remove(source);
            }
            tv_totalCount.setText("Total Students: " + studentList.size());

            return StandardListenerResult.CONTINUE;
        });
    }

    private void setManageOnClick() {
        b_manage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StudentManageActivity.class);
            startActivity(intent);
        });
    }

    protected void initView() {
        setContentView(R.layout.activity_main);
        tv_totalCount = findViewById(R.id.tv_total);
        ll_majors = findViewById(R.id.ll_majors);
        b_manage = findViewById(R.id.b_manage);
        StudentManageComponent component = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
        List<Student> students = component.getStudents();
        if (students != null && !students.isEmpty()) {
            studentList.addAll(students);
        }
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

        // 使用线性布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ll_majors.setLayoutManager(layoutManager);


        // 创建适配器并设置给 RecyclerView
        MajorAdapter mAdapter = new MajorAdapter(majorCountMap);
        ll_majors.setAdapter(mAdapter);
    }
}
