package com.example.managementx2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementx2.R;
import com.example.managementx2.beans.Comment;

import org.w3c.dom.Text;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comment> comments;
    private Context mContext;

    public CommentAdapter(List<Comment> comments, Context mContext) {
        this.comments = comments;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.comment_auth.setText(comment.getAuthor().getUsername());
        holder.comment_time.setText(comment.getCreatedAt());
        holder.content.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView comment_auth;
        private TextView comment_time;
        private TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_auth = (TextView) itemView.findViewById(R.id.member_name_card);
            comment_time = (TextView) itemView.findViewById(R.id.comment_time);
            content = (TextView) itemView.findViewById(R.id.comment_content);
        }
    }

}
