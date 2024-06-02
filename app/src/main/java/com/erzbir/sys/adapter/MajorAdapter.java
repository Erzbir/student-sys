package com.erzbir.sys.adapter;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/6/1
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.erzbir.sys.R;
import com.erzbir.sys.entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorAdapter extends RecyclerView.Adapter<MajorAdapter.ViewHolder> {

    private final Map<String, Integer> majorCountMap;
    private final Object[] majorCountEntries;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textMajorName;
        public TextView textStudentCount;

        public ViewHolder(View v) {
            super(v);
            textMajorName = v.findViewById(R.id.text_major_name);
            textStudentCount = v.findViewById(R.id.text_student_count);
        }
    }

    public MajorAdapter(Map<String, Integer> majorCountMap) {
        this.majorCountMap = majorCountMap;
        majorCountEntries = majorCountMap.entrySet().toArray();

    }

    @NonNull
    @Override
    public MajorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_major, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) majorCountEntries[position];
        holder.textMajorName.setText(entry.getKey());
        holder.textStudentCount.setText(String.valueOf(entry.getValue()));
    }

    @Override
    public int getItemCount() {
        return majorCountMap.size();
    }
}
