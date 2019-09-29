package com.labzhynskyi.wiki.model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterApi  {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/";
    private static final String TAG = "CharacterApi";
    private LoadCharacterCallback mLoadCharacterCallback;


    public interface LoadCharacterCallback {
        void onLoad(Retrofit retrofit);
    }

    public CharacterApi(LoadCharacterCallback loadCharacterCallback) {
        mLoadCharacterCallback = loadCharacterCallback;
    }

  /*   public Observable<CharacterList> getRetrofit() {
         public void getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();


        ICharacterService characterService = retrofit.create(ICharacterService.class);
             characterService.getCharacter();
        Log.d(TAG, "getRetrofit");
        mLoadCharacterCallback.onLoad((Observable<CharacterList>) characterService);

    }*/

  /*  public void getRetrofit2() {
        Retrofit retrofit;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ICharacterService characterService = retrofit.create(ICharacterService.class);



        Call<CharacterList> call = (Call<CharacterList>) characterService.getCharacter();

        call.enqueue(new Callback<CharacterList>() {
            @Override
            public void onResponse(Call<CharacterList> call, Response<CharacterList> response) {
             *//*   if (response.code() == 200) {
                    progressBar.setVisibility(View.INVISIBLE);
                    if (response.body() != null) {
                        GalleryItem.urllists.add("http://imgur.com/" + response.body().getData().getId());
                    }
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(context, "An unknown error has occured.", Toast.LENGTH_SHORT)
                            .show();
                }*//*
                Log.d("onResponse", String.valueOf(response.code()));
                Log.d("onResponse", String.valueOf(response.body().getResults().size()));

                //Log.d("urllists", String.valueOf(GalleryItem.urllists.size()));
            }
            @Override
            public void onFailure(Call call, Throwable t) {
               *//* Toast.makeText(context, "An unknown error has occured.", Toast.LENGTH_SHORT)
                        .show();*//*
            }
        });
    }
*/
    private static   Retrofit retrofit;

    public static Retrofit getRetrofit3(){



        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;

    }

}
