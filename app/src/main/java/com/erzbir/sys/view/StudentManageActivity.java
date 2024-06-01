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

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Erzbir
 * @Data: 2024/5/30 17:25
 */
public class StudentManageActivity extends PrivilegeActivity {

    private RecyclerView rv_students;
    private Button b_add;
    private Button b_cancel;
    private StudentAdapter studentAdapter;

    protected void initView() {
        setContentView(R.layout.activity_student_manage);
        rv_students = findViewById(R.id.rv_students);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv_students.setLayoutManager(layout);
        StudentManageComponent component = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
        List<Student> students = component.getStudents();
        studentAdapter = new StudentAdapter(students, student -> {
            Intent intent = new Intent(StudentManageActivity.this, StudentDetailActivity.class);
            intent.putExtra("student", student);
            startActivity(intent);
//            finish();
        });
        rv_students.setAdapter(studentAdapter);
        b_add = findViewById(R.id.b_add);
        b_cancel = findViewById(R.id.b_cancel);
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
            startActivity(intent);
//            finish();
        });
    }

    private void setCancelOnClick() {
        b_cancel.setOnClickListener(v -> {
            Intent intent = new Intent(StudentManageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

