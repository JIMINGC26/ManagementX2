package com.example.managementx2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementx2.adapters.CommentAdapter;
import com.example.managementx2.beans.Comment;
import com.example.managementx2.beans.Task;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CardActivity extends AppCompatActivity {

    private List<Comment> comments = new ArrayList<>();
    private TextView task_name;
    private TextView task_detail;
    private RecyclerView comment_recycler;

    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Bmob.initialize(this, "cc617f5b15d0a881f15b80c4f9dd3cd1");

        ActionBar bar = getSupportActionBar();
        if (bar != null){
            bar.hide();
        }

        task_name = (TextView) findViewById(R.id.card_name_act);
        task_detail = (TextView) findViewById(R.id.card_detail_act);

        Intent intent = getIntent();
        id = intent.getStringExtra("cardId");
        task_name.setText(intent.getStringExtra("cardName"));
        task_detail.setText(intent.getStringExtra("cardDetail"));

        init_Data();

    }

    private void init_Data() {
        BmobQuery<Comment> query = new BmobQuery<>();
        Task task = new Task();
        task.setObjectId(id);
        query.include("author.username");
        query.addWhereEqualTo("CommentToTask", new BmobPointer(task));
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e == null){
                    if (list.size() == 0){
                        Toast.makeText(CardActivity.this, "尚未有评论", Toast.LENGTH_SHORT).show();
                    }else {
                        comments.addAll(list);
                        init_RecyclerView();
                    }
                }else {
                    Log.d("comment", e.getMessage());
                }
            }
        });
    }

    private void init_RecyclerView() {
        comment_recycler = (RecyclerView) findViewById(R.id.comment_recyclerview);
        CommentAdapter adapter = new CommentAdapter(comments, this);
        comment_recycler.setAdapter(adapter);
        comment_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
