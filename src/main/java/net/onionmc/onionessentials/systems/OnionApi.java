package net.onionmc.onionessentials.systems;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.List;

public class OnionApi {
    public static List<String> getBaitUsernames() {
        String usernames = "";
        usernames += getListOfUsernames("https://pastebin.com/raw/mytqkWjC");
        usernames += getListOfUsernames("https://pastebin.com/raw/MQeZjhrp");
        return List.of(usernames.split("\n"));
    }

    private static String getListOfUsernames(String url) {
        Request request = new Request.Builder()
                .url(url)
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
}
