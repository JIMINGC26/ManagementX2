package com.example.managementx2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managementx2.adapters.BoardAdapter;
import com.example.managementx2.beans.Team;
import com.example.managementx2.beans.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {


    private List<Team> teamList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BoardAdapter adapter;
    FloatingActionButton floatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        //updateRelation();

        floatBtn = (FloatingActionButton) findViewById(R.id.add_btn);
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final AlertDialog dialog = builder.create();
                View dialogView = View.inflate(MainActivity.this, R.layout.dialog_create_board, null);
                dialog.setView(dialogView);
                dialog.show();
                EditText editText = (EditText) dialogView.findViewById(R.id.edittext_dialog_create_board);
                Button createBtn = (Button) dialogView.findViewById(R.id.dialog_create_board_btn1);
                Button cancleBtn = (Button) dialogView.findViewById(R.id.dialog_create_board_btn2);
                createBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Team team = new Team();
                        team.setTeamName(editText.getText().toString());
                        team.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null){
                                    teamList.add(team);
                                    adapter.notifyItemChanged(0);
                                    Toast.makeText(MainActivity.this, "创建成功！", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else {
                                    Log.d("board", e.getMessage());
                                }
                            }
                        });
                    }
                });

                cancleBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

    }

    private void initData() {
        Bmob.initialize(this,"cc617f5b15d0a881f15b80c4f9dd3cd1");
        BmobQuery<Team> query = new BmobQuery<>();
        query.include("TeamName");
        query.findObjects(new FindListener<Team>() {
            @Override
            public void done(List<Team> list, BmobException e) {
                if (e == null){
                    teamList.addAll(list);
                    initRecyclerView();
                }else {
                    Log.d("board", e.getMessage());
                }
            }
        });
    }

    private void initRecyclerView() {
        Log.d("board", String.valueOf(teamList.size()));
        recyclerView = (RecyclerView) findViewById(R.id.board_recyclerview);
        adapter = new BoardAdapter(teamList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void updateRelation(){

        User user = BmobUser.getCurrentUser(User.class);
        Team team = new Team();
        team.setObjectId("ZIvrbbbY");
        BmobRelation relation = new BmobRelation();
        relation.add(user);
        team.setUserOFTeam(relation);
        team.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    Toast.makeText(MainActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("test", e.getMessage());
                }
            }
        });
        /*user.setUserToTeam(relation);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    Toast.makeText(MainActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();

                }else {
                    Log.d("test", e.getMessage());
                }
            }
        });*/

    }


}