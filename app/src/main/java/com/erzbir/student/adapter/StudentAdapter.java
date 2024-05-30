package com.erzbir.student.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.erzbir.student.R;
import com.erzbir.student.entity.Student;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/30 17:15
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private final List<Student> studentList;
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
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewStudentID;
        TextView textViewStudentName;
        TextView textViewStudentGender;
        TextView textViewStudentMajor;
        TextView textViewStudentGrade;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStudentID = itemView.findViewById(R.id.tv_id);
            textViewStudentName = itemView.findViewById(R.id.tv_name);
            textViewStudentGender = itemView.findViewById(R.id.tv_gender);
            textViewStudentMajor = itemView.findViewById(R.id.tv_major);
            textViewStudentGrade = itemView.findViewById(R.id.tv_grade);
        }

        public void bind(final Student student, final OnItemClickListener onItemClickListener) {
            textViewStudentID.setText(String.format("Student ID: %s", student.getId()));
            textViewStudentName.setText(String.format("Name: %s", student.getName()));
            textViewStudentGender.setText(String.format("Gender: %s", student.getGender()));
            textViewStudentMajor.setText(String.format("Major: %s", student.getMajor()));
            textViewStudentGrade.setText(String.format("Grade: %s", student.getGrade()));
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(student));
        }
    }
}
