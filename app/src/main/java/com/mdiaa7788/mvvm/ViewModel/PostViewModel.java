package com.mdiaa7788.mvvm.ViewModel;

import android.app.Activity;
import android.net.DnsResolver;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
import com.mdiaa7788.mvvm.MainActivity;
import com.mdiaa7788.mvvm.MySingleton;
import com.mdiaa7788.mvvm.model.DataPostFinal;
import com.mdiaa7788.mvvm.model.DataPosts;

public class PostViewModel extends ViewModel {


   public MutableLiveData<DataPosts> postsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> posts = new MutableLiveData<>();


    public void getPosts(Activity activity) {
//        FetchData.fecthData(activity);

        try {
            final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    "http://192.168.1.2/salonApp/feedback_all/get_AllFeedBack.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

//                               Log.e("ofers json>>>>>>>>   ", String.valueOf(response.length()));
                            Log.e("ofers json>>>>>>>>   ",response);

                            if (response.length() > 0) {


                                try {
                                    Gson gson = new Gson();
                                    DataPosts dataDelevery;
                                    dataDelevery = gson.fromJson(response.toString(), DataPosts.class);

                                    postsMutableLiveData.setValue(dataDelevery);
//                                    for(int i=0;i<response.length();i++){
////                                           Log.e(">>>>",dataDelevery.getBody().get(i).getEmail());
////                                           listItems.add(new DataPostFinal(
////                                                   dataDelevery.getBody().get(i).getIdUser(),
////                                                   dataDelevery.getBody().get(i).getId(),
////                                                   dataDelevery.getBody().get(i).getFeedBack(),
////                                                   dataDelevery.getBody().get(i).getEmailUser()
////                                           ));
////                                           recyclerView_dAdapter.notifyDataSetChanged();
//                                    }
                                }catch (Exception e){
                                    Log.e("error",">>>>>>>");
                                    posts.setValue(e.getMessage().toString());
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //progressDialog.dismiss();

                            posts.setValue(error.getMessage().toString());
                            try {


                                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                                       Toast.makeText(getApplicationContext(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                                } else if (error instanceof AuthFailureError) {

                                    //startActivity(new Intent(getApplicationContext(),Log_In.class));
                                } else if (error instanceof ServerError) {
//                                       Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_LONG).show();
                                    //TODO
                                } else if (error instanceof NetworkError) {
//                                       Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_LONG).show();
                                    //TODO
                                } else if (error instanceof ParseError) {
//                                       Toast.makeText(getApplicationContext(), "ParseError", Toast.LENGTH_LONG).show();
                                    //TODO
                                }
                            }catch (Exception e){

                            }
                        }
                    }
            );
            MySingleton.getInstance(activity).addToRequestQueue(stringRequest);

        }catch (Exception r){

        }

    }


//     PostsClient.getINSTANCE().getPosts().enqueue(new Callback<List<PostModel>>() {
//        @Override
//        public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
//            postsMutableLiveData.setValue(response.body());
//        }
//
//        @Override
//        public void onFailure(Call<List<PostModel>> call, Throwable t) {
//            posts.setValue("errr");
//        }
//    });


}
