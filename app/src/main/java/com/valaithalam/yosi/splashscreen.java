package com.valaithalam.yosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class splashscreen extends AppCompatActivity {

    ImageView bulb;
    TextView wel;
    Animation bottomtop , wel_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        wel = findViewById(R.id.welcome);

        wel_anim = AnimationUtils.loadAnimation(this,R.anim.wel_anim);

        wel.setAnimation(wel_anim);


        bulb = findViewById(R.id.splash_bulb);

        bottomtop = AnimationUtils.loadAnimation(this,R.anim.bottomtop);
        bulb.setAnimation(bottomtop);

        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(1)
                .playOn(wel);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                Intent i = new Intent(splashscreen.this , MainActivity.class);
                startActivity(i);

                finish();
            }
        }, 2000);
    }
}
