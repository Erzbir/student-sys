package com.erzbir.sys.view;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.hutool.core.util.StrUtil;
import com.erzbir.event.GlobalEventChannel;
import com.erzbir.sys.AndroidApplication;
import com.erzbir.sys.R;
import com.erzbir.sys.activity.StudentDetailActivity;
import com.erzbir.sys.adapter.StudentAdapter;
import com.erzbir.sys.common.PrivilegeActivity;
import com.erzbir.sys.component.StudentManageComponent;
import com.erzbir.sys.entity.Student;
import com.erzbir.sys.event.StudentEvent;
import com.erzbir.sys.util.MethodLocker;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/30 17:25
 */
public class StudentManageActivity extends PrivilegeActivity {

    private RecyclerView rv_students;
    private EditText et_id;
    private EditText et_major;
    private Spinner sp_gender;
    private Button b_filter;
    private Button b_add;
    private Button b_delete;
    private Button b_cancel;

    protected void initView() {
        setContentView(R.layout.activity_student_manage);
        rv_students = findViewById(R.id.rv_students);
        et_id = findViewById(R.id.et_id);
        et_major = findViewById(R.id.et_major);
        sp_gender = findViewById(R.id.sp_gender);
        b_filter = findViewById(R.id.b_filter);
        b_add = findViewById(R.id.b_add);
        b_cancel = findViewById(R.id.b_cancel);
        updateInfo();
        MethodLocker.lock(String.valueOf(StudentManageActivity.class), this::registerListener);
    }

    private void updateInfo() {
        StudentManageComponent component = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
        List<Student> students = component.getStudents();
        updateInfo0(students);
    }

    private void updateInfo0(List<Student> students) {
        rv_students.removeAllViews();

        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv_students.setLayoutManager(layout);
        StudentAdapter studentAdapter = new StudentAdapter(students, student -> {
            Intent intent = new Intent(StudentManageActivity.this, StudentDetailActivity.class);
            intent.putExtra("student", student);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
//            finish();
        });
        rv_students.setAdapter(studentAdapter);
    }

    private void filter() {
        StudentManageComponent component = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
        List<Student> students = component.getStudents();
        List<Student> filteredList = students.stream()
                .filter(student -> {
                    String id = et_id.getText().toString();
                    String major = et_major.getText().toString();
                    String gender = sp_gender.getSelectedItem().toString();
                    boolean flag = true;
                    if (StrUtil.isNotBlank(id)) {
                        flag = student.getId().equals(Long.parseLong(id));
                    }
                    if (StrUtil.isNotBlank(major)) {
                        flag &= student.getMajor().equals(major);
                    }
                    if (StrUtil.isNotBlank(gender)) {
                        flag &= student.getGender().equals(gender);
                    }
                    return flag;
                }).toList();
        updateInfo0(filteredList);
    }

    private void registerListener() {
        GlobalEventChannel.INSTANCE.subscribeAlways(StudentEvent.class, event -> {
            runOnUiThread(this::updateInfo);
        });
    }

    protected void initOnClickCallback() {
        setAddOnClick();
        setCancelOnClick();
        setFilterOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

    }

    private void setFilterOnClick() {
        b_filter.setOnClickListener(v -> filter());
    }

    private void setAddOnClick() {
        b_add.setOnClickListener(v -> {
            Intent intent = new Intent(StudentManageActivity.this, StudentDetailActivity.class);
            intent.putExtra("add", 1);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
//            finish();
        });
    }

    private void setCancelOnClick() {
        b_cancel.setOnClickListener(v -> {
            Intent intent = new Intent(StudentManageActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
    }
}

