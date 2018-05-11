package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokeactivitylibrary.JokeActivity;
import com.udacity.gradle.jokes.Joker;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.PostExecuteListener {

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        progress = new ProgressDialog(this);
        progress.setTitle(getString(R.string.str_loading));
        progress.setCancelable(false);
        progress.show();
        new EndpointsAsyncTask().execute(this);
    }


    @Override
    public void onPostExecuteListener(String joke) {
        if (progress != null) {
            progress.dismiss();
        }
        final String JOKE = "JOKE";
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JOKE, joke);
        startActivity(intent);

    }
}
