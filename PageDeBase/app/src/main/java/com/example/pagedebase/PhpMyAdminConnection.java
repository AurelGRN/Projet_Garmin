package com.example.pagedebase;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PhpMyAdminConnection {

    private static final String TAG = PhpMyAdminConnection.class.getSimpleName();
    private static final String URL = "https://projet-android-garmin.iut-orsay.fr/phpmyadmin/";
    private static final String IDENTIFIANT_COURT = "aglecture";
    private static final String MOT_DE_PASSE_INFORMATIQUE = "0jxfuJogjrxeL@wY";

    public interface PhpMyAdminCallback {
        void onSuccess(String response);
        void onFailure(String error);
    }

    public static void connectToPhpMyAdmin(PhpMyAdminCallback callback) {
        new PhpMyAdminTask(callback).execute();
    }

    private static class PhpMyAdminTask extends AsyncTask<Void, Void, String> {

        private PhpMyAdminCallback callback;

        PhpMyAdminTask(PhpMyAdminCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            String credentials = Credentials.basic(IDENTIFIANT_COURT, MOT_DE_PASSE_INFORMATIQUE);
            Request request = new Request.Builder()
                    .url(URL)
                    .header("Authorization", credentials)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    return null;
                }
            } catch (IOException e) {
                Log.e(TAG, "Error: " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                callback.onSuccess(result);
            } else {
                callback.onFailure("Failed to connect to phpMyAdmin");
            }
        }
    }
}

