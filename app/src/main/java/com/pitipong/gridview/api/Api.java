package com.pitipong.gridview.api;

import com.pitipong.gridview.model.PrettyModel;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;

/**
 * Created by pw on 17/3/2559.
 */
public class Api {

    public static final String API_URL = "https://gank.io";

    private static LoadList Iapi_loadList;

    public static LoadList Iapi_loadList() {
        if (Iapi_loadList == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Iapi_loadList = retrofit.create(LoadList.class);
        }
        return Iapi_loadList;
    }

    public interface LoadList {
        @GET("/api/data/福利/213/1")
        retrofit.Call<PrettyModel> getPrettyModel();
    }

}
