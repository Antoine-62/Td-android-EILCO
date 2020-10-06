package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button rollButton = (Button) findViewById(R.id.buttonLancer);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r =new Random();

                TextView text = (TextView) findViewById(R.id.hello);
                TextView text2 = (TextView) findViewById(R.id.hello2);
                TextView FacesNumberText = (TextView) findViewById(R.id.FacesNumber);
                String FacesNumberString= FacesNumberText.getText().toString();
                int FacesNumber =6;
                if(FacesNumberString.length()!=0){
                    System.out.println("boom");
                    FacesNumber = Integer.valueOf(FacesNumberString);
                }



                int nombre1 = r.nextInt(FacesNumber)+1;
                int nombre2 = r.nextInt(FacesNumber)+1;

                text.setText("" + nombre1);
                text2.setText("" + nombre2);
            }
        });
    }
}