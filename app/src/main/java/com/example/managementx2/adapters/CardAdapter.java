package com.example.managementx2.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementx2.CardActivity;
import com.example.managementx2.R;
import com.example.managementx2.beans.Task;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Task> taskList;
    private Context context;

    public CardAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.cardName.setText(task.getTaskName());
        holder.cardDetail.setText(task.getDetail());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CardActivity.class);
                intent.putExtra("cardId", task.getObjectId());
                intent.putExtra("cardName", task.getTaskName());
                intent.putExtra("cardDetail", task.getDetail());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView cardName;
        private TextView cardDetail;
        private TextView cardMember;
        private View cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            cardName = (TextView) itemView.findViewById(R.id.card_name);
            cardDetail = (TextView) itemView.findViewById(R.id.card_detail);
            cardMember = (TextView) itemView.findViewById(R.id.card_member);
        }
    }

}
