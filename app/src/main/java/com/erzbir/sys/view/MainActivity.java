package com.erzbir.sys.view;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.erzbir.event.GlobalEventChannel;
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
import com.erzbir.sys.util.MethodLocker;

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
    private RecyclerView rv_major;
    private Button b_manage;
    private Button b_setting;
    private Button b_about;
    private List<Student> studentList = new ArrayList<>();

    protected void initOnClickCallback() {
        setManageOnClick();
        setAboutOnClick();
        setSettingOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {
        updateStudentInfo();
        MethodLocker.lock(String.valueOf(MainActivity.class),
                this::registerListener);

    }

    private void registerListener() {
        GlobalEventChannel.INSTANCE.subscribeAlways(StudentEvent.class, event -> {
            runOnUiThread(() -> {
                Student source = event.getSource();
                if (event instanceof StudentAddEvent || event instanceof StudentUpdateEvent) {
                    studentList.add(source);
                } else if (event instanceof StudentDeleteEvent) {
                    studentList.remove(source);
                }
                updateStudentInfo();
            });
        });
    }

    private void setManageOnClick() {
        b_manage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StudentManageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
    }

    private void setSettingOnClick() {
        b_setting.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
    }

    private void setAboutOnClick() {
        b_about.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
    }

    protected void initView() {
        setContentView(R.layout.activity_main);
        tv_totalCount = findViewById(R.id.tv_total);
        rv_major = findViewById(R.id.rv_major);
        b_manage = findViewById(R.id.b_manage);
        b_about = findViewById(R.id.b_about);
        b_setting = findViewById(R.id.b_setting);
    }

    private void updateStudentInfo() {
        StudentManageComponent component = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
        studentList = component.getStudents();
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

        rv_major.removeAllViews();

        // 使用线性布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_major.setLayoutManager(layoutManager);


        // 创建适配器并设置给 RecyclerView
        MajorAdapter mAdapter = new MajorAdapter(majorCountMap);
        rv_major.setAdapter(mAdapter);
    }
}
