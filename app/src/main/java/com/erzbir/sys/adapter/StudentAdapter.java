package com.erzbir.sys.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.erzbir.sys.AndroidApplication;
import com.erzbir.sys.R;
import com.erzbir.sys.application.DefaultApplication;
import com.erzbir.sys.component.StudentManageComponent;
import com.erzbir.sys.entity.Student;
import com.erzbir.sys.event.StudentDeleteEvent;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/30 17:15
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Student student);
    }

    public StudentAdapter(List<Student> studentList, OnItemClickListener onItemClickListener) {
        this.studentList = studentList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.bind(student, onItemClickListener);
        holder.b_delete.setOnClickListener(v -> {
            DefaultApplication.INSTANCE.dispatchEvent(new StudentDeleteEvent(student));
            StudentManageComponent component = AndroidApplication.INSTANCE.APP.getComponent(StudentManageComponent.class);
            component.remove(student);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_gender;
        TextView tv_major;
        TextView tv_grade;
        Button b_delete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_gender = itemView.findViewById(R.id.tv_gender);
            tv_major = itemView.findViewById(R.id.tv_major);
            tv_grade = itemView.findViewById(R.id.tv_grade);
            b_delete = itemView.findViewById(R.id.b_delete);
        }

        public void bind(final Student student, final OnItemClickListener onItemClickListener) {
            tv_id.setText(String.format("Student ID: %s", student.getId()));
            tv_name.setText(String.format("Name: %s", student.getName()));
            tv_gender.setText(String.format("Gender: %s", student.getGender()));
            tv_major.setText(String.format("Major: %s", student.getMajor()));
            tv_grade.setText(String.format("Grade: %s", student.getGrade()));
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(student));
        }
    }
}
