package com.udacity.gradle.builditbigger;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.example.jokeactivitylibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.jokes.Joker;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Context, Void, Pair<Context, String>> {
    private MyApi myApiService = null;
    private ProgressDialog progress;

    @Override
    protected Pair<Context, String> doInBackground(final Context... params) {
        Context context = null;
        if (params != null && params.length != 0) {
            context = params[0];
            ((Activity)context).runOnUiThread(new Runnable() {
                public void run() {
                    progress = new ProgressDialog(params[0]);
                    progress.setTitle(params[0].getString(R.string.str_loading));
                    progress.setCancelable(false);
                    progress.show();
                }
            });
        }
        if (myApiService == null) {
            assert context != null;
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(context.getString(R.string.ip_address))
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest)
                                throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return new Pair<>(context, myApiService.getJoke().execute().getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Pair<>(context, "");
    }

    @Override
    protected void onPostExecute(Pair<Context, String> pair) {
        if (progress != null) {
            progress.dismiss();
        }
        Context context = pair.first;
        if(context != null) {
            final String JOKE = "JOKE";
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JOKE, pair.second);
            context.startActivity(intent);
        }
    }
}
