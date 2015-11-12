package com.nelsoft.droiddit.web;

import android.graphics.Bitmap;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by barry on 11/11/15.
 */
public interface WebService {

    @GET("{url}")
    Bitmap getBitmap(String url);

    @GET("{url}")
    Bitmap getBitmap(String url, Callback<Bitmap> callback);

    public static class Implementation {

        public static WebService get() {
            return getBuilder()
                    .build()
                    .create(WebService.class);
        }

        static RestAdapter.Builder getBuilder() {
            return new RestAdapter.Builder();
        }

    }

}
