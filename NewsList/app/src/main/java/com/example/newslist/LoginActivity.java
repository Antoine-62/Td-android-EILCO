package com.example.newslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle(getLocalClassName());



        Button rollButton = (Button) findViewById(R.id.buttonLogin);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) findViewById(R.id.hello);
                text.setText("chargement");
                Intent intent = new Intent(LoginActivity.this, NewsActivity.class);
                TextView name = (TextView) findViewById(R.id.Name);
                String nameString= name.getText().toString();
                NewsListApplication app = (NewsListApplication) getApplicationContext();
                app.setLogin(nameString);
                intent.putExtra("login",nameString);
                startActivity(intent);
                finish();
            }
        });

    }
}
