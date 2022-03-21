package com.mdiaa7788.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.mdiaa7788.mvvm.ViewModel.PostViewModel;
import com.mdiaa7788.mvvm.adaptor.Offers_Recycal_ViewHolder;
import com.mdiaa7788.mvvm.model.DataPostFinal;
import com.mdiaa7788.mvvm.model.DataPosts;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener ,OnItemClick{


    RecyclerView recycal_deliverys;
    private Offers_Recycal_ViewHolder recyclerView_dAdapter;
    public List<DataPostFinal> listItems = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;

    int REGISTER_URL=0;
    int REGISTER_URL_mareds;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    SwipeRefreshLayout mSwipeRefreshLayout;

    String deirection;

    PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postViewModel = ViewModelProviders.of(MainActivity.this).get(PostViewModel.class);
        postViewModel.getPosts(MainActivity.this);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                // Fetching data from server
                startUI();
            }
        });


        postViewModel.posts.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mSwipeRefreshLayout.setRefreshing(false);
                Log.e("error is :  >> ",s);
            }
        });
        postViewModel.postsMutableLiveData.observe(this, new Observer<DataPosts>() {
            @Override
            public void onChanged(DataPosts postModels) {
                mSwipeRefreshLayout.setRefreshing(false);
                Log.e("ttt>>>>>",""+postModels.getBody().size());
                for(int i=0;i<postModels.getBody().size();i++){
                    listItems.add(new DataPostFinal(
                            postModels.getBody().get(0).getId(),
                            postModels.getBody().get(0).getId(),
                            postModels.getBody().get(0).getFeedBack(),
                            postModels.getBody().get(0).getEmail()
                    ));
                    recyclerView_dAdapter.notifyDataSetChanged();
                }

            }
        });
    }



    @Override
    public void onRefresh() {
        startUI();
    }


    private void startUI() {
        listItems.clear();
        REGISTER_URL=0;
        recycal_deliverys = findViewById(R.id.recycal_allcatigry);
        recycal_deliverys.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recycal_deliverys.setLayoutManager(gridLayoutManager);

        recyclerView_dAdapter = new Offers_Recycal_ViewHolder(listItems,MainActivity.this, getApplicationContext(),
                deirection,this);
        recycal_deliverys.setAdapter(recyclerView_dAdapter);

//        getjson(0);

//        recycal_deliverys.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                isScrolling=true;
//                currentItems = gridLayoutManager.getChildCount();
//                totalItems = gridLayoutManager.getItemCount();
//                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();
//                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == listItems.size() - 1) {
//                    if (REGISTER_URL<=REGISTER_URL_mareds) {
//                        isScrolling = false;
//                        getjson(REGISTER_URL);
//                    }
//
//                }
//                //
//
//            }
//        });
    }

    @Override
    public void onClick(DataPostFinal value) {
        Log.e("getBody  :  ",value.getBody());
        Log.e("getTitle  :  ",value.getTitle());
    }


//    @Override
//    public void onClick(String value) {
//
//        Log.e("onClick","onClick"+value);
//    }
}