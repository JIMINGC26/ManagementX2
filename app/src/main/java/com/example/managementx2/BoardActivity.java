package com.example.managementx2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementx2.adapters.CardAdapter;
import com.example.managementx2.adapters.MemberAdapter;
import com.example.managementx2.beans.Task;
import com.example.managementx2.beans.Team;
import com.example.managementx2.beans.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class BoardActivity extends AppCompatActivity {

    private RecyclerView cardView;
    private RecyclerView memberView;
    private Button addBtn;
    private MemberAdapter memberAdapter;
    private CardAdapter cardAdapter;
    private TextView boardName;

    private String teamId;
    private String teamName;
    private List<User> users = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        Bmob.initialize(this, "cc617f5b15d0a881f15b80c4f9dd3cd1");
        ActionBar bar = getSupportActionBar();
        if (bar != null){
            bar.hide();
        }

        boardName = (TextView) findViewById(R.id.board_name_act);

        Intent intent = getIntent();
        boardName.setText(intent.getStringExtra("teamName"));
        teamName = boardName.getText().toString();
        teamId = intent.getStringExtra("teamId");

        initData_member();
        initData_task();

    }

    private void initData_member() {
        BmobQuery<User> query = new BmobQuery<>();
        Team team = new Team();
        team.setObjectId(teamId);
        query.include("username");
        Log.d("id", teamId);
        query.addWhereRelatedTo("UserToTeam", new BmobPointer(team));
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null){
                   if (list.size() == 0){
                       Toast.makeText(BoardActivity.this, "暂无成员！", Toast.LENGTH_SHORT).show();
                   }else {
                       users.addAll(list);
                       initMemberRecyclerView();
                   }
                }else {
                    Log.d("users", e.getMessage());
                }
            }
        });

    }

    private void initMemberRecyclerView() {
        memberView = (RecyclerView) findViewById(R.id.member_List);
        memberAdapter = new MemberAdapter(users, this);
        memberView.setAdapter(memberAdapter);
        memberView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    private void initData_task() {

        BmobQuery<Task> query = new BmobQuery<>();
        Team team = new Team();
        team.setObjectId(teamId);
        query.include("taskName");
        query.include("detail");
        query.addWhereEqualTo("TaskToTeam", new BmobPointer(team));
        query.findObjects(new FindListener<Task>() {
            @Override
            public void done(List<Task> list, BmobException e) {
                if (e == null){
                    if (list.size() == 0){
                        Toast.makeText(BoardActivity.this, "尚未分配任务", Toast.LENGTH_SHORT).show();
                    }else {
                        taskList.addAll(list);
                        initCardRecyclerView();
                    }
                }else {
                    Log.d("card", e.getMessage());
                }
            }
        });
    }

    private void initCardRecyclerView() {
        cardView = (RecyclerView) findViewById(R.id.card_recyclerview);
        cardAdapter = new CardAdapter(taskList, this);
        cardView.setAdapter(cardAdapter);
        cardView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }
}
