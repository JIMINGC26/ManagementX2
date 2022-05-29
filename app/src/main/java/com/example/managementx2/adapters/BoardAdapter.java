package com.example.managementx2.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementx2.BoardActivity;
import com.example.managementx2.R;
import com.example.managementx2.beans.Team;
import com.example.managementx2.beans.User;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private List<Team> teamList;
    private Context context;

    public BoardAdapter(List<Team> teamList, Context context) {
        this.teamList = teamList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.boardName.setText(team.getTeamName());

        //holder.itemView.setBackgroundColor();
        holder.itemView.setOnClickListener((v) -> {
            Intent intent = new Intent(context, BoardActivity.class);
            intent.putExtra("teamId", team.getObjectId());
            intent.putExtra("teamName", team.getTeamName());
            Toast.makeText(context, "看板详情", Toast.LENGTH_SHORT).show();
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView boardName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            boardName = (TextView) itemView.findViewById(R.id.board_name);
        }
    }



}
