package com.erzbir.sys.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.erzbir.sys.AndroidApplication;
import com.erzbir.sys.adapter.StudentListAdapter;
import com.erzbir.sys.component.StudentManageComponent;
import com.erzbir.sys.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class DetailActivity extends AppCompatActivity {
    private List<Student> students;
    private RecyclerView rv_bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        StudentManageComponent studentManageComponent = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
        students = studentManageComponent.stream().map(student -> (Student) student).collect(Collectors.toList());
        StudentListAdapter adapter = new StudentListAdapter(DetailActivity.this, students);
        StaggeredGridLayoutManager sm = new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL, 1);
        rv_bills.setLayoutManager(sm);
        rv_bills.setAdapter(adapter);
    }

    private void initView() {
//        setContentView(R.layout.activity_detail);
//        rv_bills = findViewById(R.id.rv_bills);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

}
