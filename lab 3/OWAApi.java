package MINWD3;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OWAApi {


    private static OWAApi api;
    private OkHttpClient httpClient;

    private final static String TOKEN = "a58464bc6921106bbd498d9d024119a0";
    private final static String URL = "http://api.openweathermap.org/data/2.5/weather";

    public OWAApi() {
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

    }

    public static OWAApi getInstance(){
        if (api == null) api = new OWAApi();
        return api;
    }


    public JSONObject getWeather(String city) {
        Request request = getRequestBuilder(URL+"?APPID="+TOKEN+"&q="+city+"&units=metric&lang=pl")
                .addHeader("Content-Type", "text/plain")
                .get()
                .build();

        String responseBody = null;
        try {
            Response response = httpClient.newCall(request).execute();
            responseBody = response.body().string();

            response.close();
        } catch (IOException e) {
           e.printStackTrace();
        }

        return new JSONObject(responseBody);
    }


    private Request.Builder getRequestBuilder(String url) {
        return (new Request.Builder()
                .url(url)
        );
    }

}
