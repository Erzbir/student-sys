package com.erzbir.student.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.erzbir.event.GlobalEventChannel;
import com.erzbir.event.StandardListenerResult;
import com.erzbir.student.AndroidApplication;
import com.erzbir.student.R;
import com.erzbir.student.activity.AddStudentActivity;
import com.erzbir.student.component.StudentManageComponent;
import com.erzbir.student.entity.IStudent;
import com.erzbir.student.event.StudentAddEvent;
import com.erzbir.student.event.StudentDeleteEvent;
import com.erzbir.student.event.StudentEvent;
import com.erzbir.student.event.StudentUpdateEvent;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class MainActivity extends AppCompatActivity {
    private TextView tv_total;
    private TextView tv_totalPay;
    private TextView tv_totalIncome;
    private Button bt_addBill;
    private Button bt_detail;
    private Button bt_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initOnClickCallback();
        GlobalEventChannel.INSTANCE.subscribe(StudentEvent.class, event -> {
            IStudent source = event.getSource();
            Class<? extends StudentEvent> eventClass = event.getClass();
            if (StudentAddEvent.class.equals(eventClass) || StudentUpdateEvent.class.equals(eventClass)) {

            }
            if (StudentDeleteEvent.class.equals(eventClass)) {

            }
            tv_total.setText(String.valueOf(Float.parseFloat(tv_totalPay.getText().toString()) + Float.parseFloat(tv_totalIncome.getText().toString())));
            return StandardListenerResult.CONTINUE;
        });
    }

    private void initView() {
        tv_total = findViewById(R.id.tv_total);
        tv_totalPay = findViewById(R.id.tv_totalPay);
        tv_totalIncome = findViewById(R.id.tv_totalIncome);
        bt_addBill = findViewById(R.id.bt_addBill);
        bt_detail = findViewById(R.id.bt_detail);
        bt_setting = findViewById(R.id.bt_setting);
        StudentManageComponent studentManageComponent = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
        List<IStudent> students = studentManageComponent.getStudents();
        float total = 0, totalPay = 0, totalIncome = 0;
        for (IStudent student : students) {

        }
        tv_total.setText(String.valueOf(total));
        tv_totalPay.setText(String.valueOf(totalPay));
        tv_totalIncome.setText(String.valueOf(totalIncome));
    }

    private void initOnClickCallback() {
        setDetailOnClick();
        setAddBillOnClick();
        setSettingOnClick();
    }

    private void setAddBillOnClick() {
        bt_addBill.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivity(intent);
        });
    }

    private void setSettingOnClick() {
        bt_setting.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        });
    }

    private void setDetailOnClick() {
        bt_detail.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            startActivity(intent);
        });
    }
}
