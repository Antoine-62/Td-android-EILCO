package com.example.td6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button rollButton = (Button) findViewById(R.id.buttonSearch);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                TextView name = (TextView) findViewById(R.id.Name);
                String nameString = name.getText().toString();
                paramSearch app = (paramSearch) getApplicationContext();
                app.setSearch(nameString);
                startActivity(intent);
                finish();
            }
        });
    }
}
