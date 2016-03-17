package com.pitipong.gridview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.pitipong.gridview.R;
import com.pitipong.gridview.adapter.ListAdapter;
import com.pitipong.gridview.api.Api;
import com.pitipong.gridview.model.PrettyModel;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Toolbar toolbar;
    GridView mGridView;
    ListAdapter mListAdapter;
    PrettyModel prettyModel;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPrettyList();
    }

    private void initView(final PrettyModel prettyModel){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_load_gallery);
        mProgressBar.setVisibility(View.VISIBLE);

        mGridView = (GridView) findViewById(R.id.gridView);
        mListAdapter = new ListAdapter(MainActivity.this,prettyModel);
        mGridView.setAdapter(mListAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url  = prettyModel.getResults().get(position).getUrl();
                String name = prettyModel.getResults().get(position).getWho();
                Intent intent = new Intent(MainActivity.this, FullScreenActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }

    public void loadPrettyList(){
        Call<PrettyModel> prettyModelCall = Api.Iapi_loadList().getPrettyModel();
        prettyModelCall.enqueue(new Callback<PrettyModel>() {
            @Override
            public void onResponse(Response<PrettyModel> response, Retrofit retrofit) {
                prettyModel = response.body();
                initView(prettyModel);
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
