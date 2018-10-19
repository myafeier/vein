package com.ynpulse.hht_juai;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ynpulse.hht_juai.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Students extends AppCompatActivity {

    private Toolbar toolbar=null;
    private ActionBar actionBar=null;

    private List<User> testUsers;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        setActionBar();
        initUsers();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        View view=this.getLayoutInflater().inflate(R.id.list_students)
        listView=findViewById(R.id.list_students);
        listView.setAdapter(new StudentAdapter(this,testUsers));

    }

    protected void setActionBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:
            }
        });
    }

    protected  void initUsers(){
        testUsers=new ArrayList<User>();
        for (int i=0;i<100;i++){
            testUsers.add(new User(i,"uuid"+i,"姓名"+i,"vein"+i,"https://imgsa.baidu.com/zhixin/abpic/item/b0742dfa828ba61ef951bdf84334970a314e596e.jpg"));
        }
    }


}
