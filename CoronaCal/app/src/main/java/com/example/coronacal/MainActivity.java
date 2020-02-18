package com.example.coronacal;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnNavTohorizontal = findViewById(R.id.btnGoTohorizontal);
        btnNavTohorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DBView.class));
                if( tryConnect(true) ){ insertTest(); }

            }
        });


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
    private boolean tryConnect(boolean showMessage) {
        try {
            if (conn != null && !conn.isClosed())
                return true;
            // TODO 어디선가 정보를 입력받아옴.
              String dbIp = "db-estrella0509.godohosting.com"; // 뒤에 :1443 은 입력하지 않는다.
              String dbName = "estrella0509_godohosting_com";
              String dbUser = "estrella0509";
              String dbUserPass = "@soja30003";
              ConnectionClass connClass = new ConnectionClass();
              conn = connClass.getConnection(dbUser, dbUserPass, dbName, dbIp);
              if (conn == null) {
                  if (showMessage) showToast(connClass.getLastErrMsg());
              return false;
        } else {
            if (conn.isClosed()) {

                if (showMessage) System.out.println("DataBase 연결 실패.");
             return false;
              } else {
                  if (showMessage) System.out.println("DataBase 연결에 성공했습니다.");
                  return true;
            }
              }
        } catch (SQLException e) {
            if (showMessage) System.out.println(e.getMessage());
         e.printStackTrace();
        return false;
    }
}

        private void insertTest(){
    String regDate = android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss",
            new java.util.Date()).toString();
    String query = String.format("INSERT INTO Site (siteName, url, cssQuery) VALUES ('%s','%s','%s')" , "naver", "naver.com", "<br>");
    Pair<String, Integer> result = executeQuery(query);
}
private Pair<String, Integer> executeQuery(String query){
    try {
        if( tryConnect(true) ){
            // delete test
            PreparedStatement preStmt = conn.prepareStatement(query);
            int delCnt = preStmt.executeUpdate();
            return Pair.create("", delCnt);
        }else return Pair.create("DB에 연결할 수 없습니다.", 0);
    } catch (SQLException e) {
        e.printStackTrace();
        return Pair.create("실패 : " + e.getMessage(), 0);
    }
}
private void showToast(String text){
    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
}

    }
