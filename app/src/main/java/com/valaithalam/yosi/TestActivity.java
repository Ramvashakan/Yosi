package com.valaithalam.yosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {

    Button test1,test4,test3,test2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        test1 = findViewById(R.id.bn_test1);
        test2 = findViewById(R.id.bn_test2);
        test3 = findViewById(R.id.bn_test3);
        test4 = findViewById(R.id.bn_test4);


        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestActivity.this, Test1Activity.class);
                startActivity(i);
            }
        });

        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestActivity.this, Test2Activity.class);
                startActivity(i);
            }
        });
        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestActivity.this, Test3Activity.class);
                startActivity(i);
            }
        });
        test4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestActivity.this, Test4Activity.class);
                startActivity(i);
            }
        });


    }
}
