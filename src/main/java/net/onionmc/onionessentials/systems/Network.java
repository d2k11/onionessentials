package net.onionmc.onionessentials.systems;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.List;

public class Network {

    public static String endpoint = "http://127.0.0.1:5000/v1/";

    /**
     * Get a string from the API
     *
     * @param url The URL to get the string from
     * @return The string
     */
    public String getString(String url) {
        Request request = new Request.Builder()
                .url(this.endpoint + url)
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Unexpected code " + response);
            }

            return response.body().string();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get a JSON object from the API
     *
     * @param url The URL to get the JSON from
     * @return The JSON object
     */
    public JSONObject getJson(String url) {
        Request request = new Request.Builder()
                .url(this.endpoint + url)
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Unexpected code " + response);
            }

            // Convert the response body to a JSONObject
            String responseBody = response.body().string();
            return (JSONObject) new JSONParser().parse(responseBody);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Post a JSON object to the API
     *
     * @param url          The URL to post the JSON to
     * @param jsonInput    The JSON object to post
     * @param responseType The type of the response
     * @param <T>          The type of the response
     * @return The response object
     * @throws Exception
     */
    public <T> T postJson(String url, String jsonInput, Class<T> responseType) throws Exception {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(jsonInput, JSON);
        Request request = new Request.Builder()
                .url(this.endpoint + url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new Exception("Unexpected code " + response);
            String responseBody = response.body().string();
            Gson gson = new Gson();
            return gson.fromJson(responseBody, responseType);
        }
    }
}
