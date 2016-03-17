package com.pitipong.gridview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pitipong.gridview.R;
import com.pitipong.gridview.TouchImageView;

public class FullScreenActivity extends AppCompatActivity {

    Toolbar toolbar;
    String name, url;
    TouchImageView touchImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        initView();
    }

    public void initView() {
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
        name = bundle.getString("name");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.w));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        touchImageView = (TouchImageView) findViewById(R.id.img_full);

        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .crossFade()
                .into(touchImageView);
    }
}
