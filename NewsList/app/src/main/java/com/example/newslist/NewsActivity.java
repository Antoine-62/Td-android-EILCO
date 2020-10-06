package com.example.newslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NewsListApplication app = (NewsListApplication) getApplicationContext();
        String login2 = app.getLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity);
        setTitle(getLocalClassName());
        Intent intent = getIntent();
        String login;
        if (intent.hasExtra("login")) {
            login = intent.getStringExtra("login");
            TextView text = (TextView) findViewById(R.id.hello);
            text.setText("" + login);

        }
        Button rollButton = (Button) findViewById(R.id.ok);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsActivity.this, DetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button rollButton2 = (Button) findViewById(R.id.news);
        rollButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://news.google.com/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                finish();
            }
        });
        /*public void onBackPressed() {
            super.onBackPressed();   //TODO
        }*/
    }
}
