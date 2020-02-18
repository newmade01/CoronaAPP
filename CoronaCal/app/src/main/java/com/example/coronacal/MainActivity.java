package com.example.coronacal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Button btnNavToSecond = findViewById(R.id.btnGoToSecondScreen);
        btnNavToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        Button btnNavToManager = findViewById(R.id.btnGoToLockScreen);
        btnNavToManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //크롤링 샘플코드
              new Thread() {
                    public void run() {
                        System.out.println("크롤링 시작");
                        Crawling cw = new Crawling();
                        String result = cw.getSite("http://shecs.co.kr/","div [id=lnb]");
                        System.out.println("결과: "+result);
                    }
                }.start();

                startActivity(new Intent(MainActivity.this, LockActivity.class));
         }
        });
    }
}
