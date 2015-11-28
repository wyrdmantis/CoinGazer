package com.codemantis.gzdr.coingazer;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gzdr on 07/07/2015.
 */
public class JSONParser {

    public String makeHttpRequest()throws IOException {


        URL urlNew = new URL("https://api.bitcoinaverage.com/ticker/global/USD/last");
        HttpURLConnection urlConnection = (HttpURLConnection) urlNew.openConnection();
        String valueString = "";

        try {
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());
            valueString = readStream(is);

        }finally {
            urlConnection.disconnect();
        }
        return valueString;
    }

    private String readStream(InputStream is)throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
