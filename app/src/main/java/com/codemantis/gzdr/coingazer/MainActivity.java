package com.codemantis.gzdr.coingazer;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity {

    public JSONParser jsonparser = new JSONParser();
    public TextView tv;
    public String jobj = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView1);


        new retrieve_data().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class retrieve_data extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... arg0) {


            try {
                jobj = jsonparser.makeHttpRequest();

            } catch (IOException e) {
                e.printStackTrace();
            }

            // check your log for json response
            Log.d("Login attempt", jobj);

            return jobj;
        }

        protected void onPostExecute(String jobj) {

            tv.setText("1 BTC = " + jobj + " $");
        }


    }
}
