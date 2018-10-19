package com.ynpulse.hht_juai;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.ynpulse.hht_juai.dropdown_menu.DropDownMenu;

public class BiosignatureActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;

    private LinearLayout layout;
    private View listItem;
    private View listView;
    private DropDownMenu dropDownMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biosignature);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();
    }

    protected void initView(){

        layout= (LinearLayout) getLayoutInflater().inflate(R.layout.pup_selectlist,null,false);
    }


    protected void setupToolbar(){
        mToolbar= findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mActionBar=getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
