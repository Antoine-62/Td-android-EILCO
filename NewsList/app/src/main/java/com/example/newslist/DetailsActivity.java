package com.example.newslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsactivity);
        setTitle(getLocalClassName());
        NewsListApplication app = (NewsListApplication) getApplicationContext();
        String login2 = app.getLogin();
        TextView text = (TextView) findViewById(R.id.hello);
        text.setText("" + login2);
        Button rollButton = (Button) findViewById(R.id.buttonDetails);
        Button rollButton2 = (Button) findViewById(R.id.logout);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, NewsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rollButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
