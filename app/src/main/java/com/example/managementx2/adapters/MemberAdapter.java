package com.example.managementx2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementx2.R;
import com.example.managementx2.beans.User;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    private List<User> users;
    private Context context;

    public MemberAdapter(List users, Context context){
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.memberName.setText(user.getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView memberName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            memberName = (TextView) itemView.findViewById(R.id.member_name);
        }
    }

}
