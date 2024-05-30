package com.erzbir.student.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.erzbir.student.R;
import com.erzbir.student.activity.EditStudentActivity;
import com.erzbir.student.entity.Student;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class StudentDetailAdapter extends RecyclerView.Adapter<StudentDetailAdapter.ViewHolder> {
    private Context context;
    private List<Student> students;

    public StudentDetailAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @NotNull
    @Override
    public StudentDetailAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recy_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentDetailAdapter.ViewHolder viewHolder, int i) {
        final Student student = students.get(i);
        viewHolder.item_name.setText(student.getName());
        View editBtn = viewHolder.itemView.findViewById(R.id.bt_billEdit);
        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditStudentActivity.class);
            intent.putExtra("student", student);
            context.startActivity(intent);
            ((Activity) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        TextView item_money;
        TextView item_type;
        TextView item_detail;
        TextView item_time;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_money = itemView.findViewById(R.id.item_money);
            item_type = itemView.findViewById(R.id.item_type);
            item_detail = itemView.findViewById(R.id.item_detail);
            item_time = itemView.findViewById(R.id.item_time);
        }
    }
}
