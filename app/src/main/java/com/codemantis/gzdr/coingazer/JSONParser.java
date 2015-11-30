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
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            valueString = readStream(inputStream);

        }finally {
            urlConnection.disconnect();
        }
        return valueString;
    }

    private String readStream(InputStream stream)throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream),1000);
        for (String line = reader.readLine(); line != null; line =reader.readLine()){
            builder.append(line);
        }
        stream.close();
        return builder.toString();
    }
}
