package com.codemantis.gzdr.coingazer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    JSONParser jsonparser = new JSONParser();
    TextView tv;

    String ab;
    JSONObject jobj = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView1);



        new retrieve_data().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    class retrieve_data extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            jobj = jsonparser.makeHttpRequest("https://api.bitcoinaverage.com/ticker/global/USD");

            // check your log for json response
            Log.d("Login attempt", jobj.toString());

            try {
                ab = jobj.getString("last");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ab;
        }
        protected void onPostExecute(String ab){

            tv.setText("1 BTC = "+ab+" $");
        }



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
}
