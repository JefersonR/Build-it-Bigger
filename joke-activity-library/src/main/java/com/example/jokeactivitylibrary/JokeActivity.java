package com.example.jokeactivitylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        setupView();
    }

    private void setupView() {
        final String JOKE = "JOKE";
        TextView tvJoke = (TextView) findViewById(R.id.tv_joke);
        if(getIntent() != null && getIntent().hasExtra(JOKE))
        tvJoke.setText( getIntent().getStringExtra(JOKE));
    }


}
