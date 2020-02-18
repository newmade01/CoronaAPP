package com.example.coronacal;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBView extends Activity {
    ListView listview;
    ArrayAdapter<String> adapter;
    MyAsyncTask mTask;
    static String query = "select * from state order by curdate desc " ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_margin);

        listview = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        handler.sendEmptyMessage(0);
    }


    class MyAsyncTask extends AsyncTask<String, Void, ArrayList<String>>
    {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }


        @Override
        protected ArrayList<String> doInBackground( String... params){


            ArrayList<String> list = new ArrayList<String>();



            ResultSet reset = null;
            Connection conn = null;



            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");

                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://211.233.51.51; DatabaseName=estrella0509_godohosting_com","estrella0509","@soja30003");
                Statement stmt = conn.createStatement();
                reset = stmt.executeQuery(query);



                while(reset.next()){

                    if ( isCancelled() ) break;

                    final String str = reset.getString(1)+" "+reset.getString(3)+" "+reset.getString(4);
                    list.add(str);


                }
                conn.close();
            }

            catch (Exception e)
            {
                Log.w("111Error connection", "" + e.getMessage());
            }

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list){

            adapter.clear();
            adapter.addAll(list);


            adapter.notifyDataSetChanged();
            handler.sendEmptyMessageDelayed(0, 1000);

        }

        @Override
        protected void onCancelled(){
            super.onCancelled();
        }
    }

    public Handler handler = new Handler(){
        public void handleMessage( Message msg){
            super.handleMessage(msg);

            mTask = new MyAsyncTask();

            mTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        }
    };
}