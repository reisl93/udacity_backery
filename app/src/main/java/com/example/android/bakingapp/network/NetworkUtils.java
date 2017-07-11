package com.example.android.bakingapp.network;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private final static String TAG = NetworkUtils.class.getSimpleName();

    public static <T> T getObjectFromHttpUrlJsonResponse(final URL url, final Class<T> tClass) throws IOException {
        return new Gson().fromJson(getResponseFromHttpUrl(url), tClass);
    }

    public static @Nullable
    String getResponseFromHttpUrl(final URL url) throws IOException {
        Log.d(TAG, "Loading url " + url);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}

