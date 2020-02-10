package com.valaithalam.yosi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Practice extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ =1;

    public static final String SHARED_PREFS ="sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private int high_sre;


    TextView high_score;

    Button easy,medium,difficult,time,ci;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        easy = findViewById(R.id.bn_speed);
        medium = findViewById(R.id.bn_ages);
        difficult = findViewById(R.id.bn_probability);
        time = findViewById(R.id.bn_time);
        ci = findViewById(R.id.bn_ci);



        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Practice.this, SpeedAndDistance.class);
                startActivityForResult(i, REQUEST_CODE_QUIZ);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Practice.this, Ages.class);
                startActivity(i);

            }
        });


        difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Practice.this, Probability.class);
                startActivity(i);

            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Practice.this,Time.class);
                startActivity(i);
            }
        });

        ci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Practice.this,Compound_in.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ){

            if (resultCode == RESULT_OK){
                int score = data.getIntExtra(SpeedAndDistance.EXTRA_SCORE,0);
                if (score > high_sre){
                    //updateHighScore(score);

                }
            }


        }
    }

    /*private void loadHigh(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        high_sre = prefs.getInt(KEY_HIGHSCORE,0);
        high_score.setText("Last Time Score: "+high_sre);

    }

    private void updateHighScore(int score) {
        high_sre = score;
        high_score.setText("Last Time Score: "+high_sre);

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_HIGHSCORE, high_sre);
        editor.apply();

    }*/
}
