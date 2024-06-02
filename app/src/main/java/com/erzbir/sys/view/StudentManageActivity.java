package com.erzbir.sys.view;

import android.content.Intent;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private Button b_add;
    private Button b_cancel;

    protected void initView() {
        setContentView(R.layout.activity_student_manage);
        rv_students = findViewById(R.id.rv_students);
        b_add = findViewById(R.id.b_add);
        b_cancel = findViewById(R.id.b_cancel);
        updateInfo();
        MethodLocker.lock(String.valueOf(StudentManageActivity.class), this::registerListener);
    }

    private void updateInfo() {
        StudentManageComponent component = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
        List<Student> students = component.getStudents();

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

    private void registerListener() {
        GlobalEventChannel.INSTANCE.subscribeAlways(StudentEvent.class, event -> {
            runOnUiThread(this::updateInfo);
        });
    }

    protected void initOnClickCallback() {
        setAddOnClick();
        setCancelOnClick();
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected void initLast() {

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

