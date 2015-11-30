package com.codemantis.gzdr.coingazer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    public JSONParser jsonparser = new JSONParser();
    public TextView tv1;
    public TextView tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //CALL YOUR ASYNC TASK HERE.
                new retrieve_data().execute();
            }
        };

        Timer timer = new Timer();

        //DELAY: the time to the first execution
        //PERIODICAL_TIME: the time between each execution of your task.
        timer.schedule(timerTask, 0, 20000);

    }

    class retrieve_data extends AsyncTask<String, String, String> {

        private String jobj;

        @Override
        protected String doInBackground(String... arg0) {


            try {
                jobj = jsonparser.makeHttpRequest();

            } catch (IOException e) {
                e.printStackTrace();
            }

            // check your log for json response
            //Log.d("Login attempt", jobj);
            if (jobj!=null){
                return jobj;
            }else{
                jobj = "no internet";
                return jobj;
            }


        }

        protected void onPostExecute(String jobj) {
            jobj = this.jobj;
            if (jobj.equalsIgnoreCase("no internet")){
                tv1.setText(jobj);
                tv2.setText("");
            }else{

                tv1.setText(R.string.one_bitcoin_is);
                tv2.setText(jobj + R.string.dollar_sign);
            }
        }


    }
}
