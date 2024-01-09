package com.example.pagedebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import android.os.AsyncTask;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:mysql://projet-android-garmin.iut-orsay.fr/phpmyadmin/";
    private static final String USERNAME = "aglecture";
    private static final String PASSWORD = "0jxfuJogjrxeL@wY";

    public interface DatabaseCallback {
        void onSuccess(String response);
        void onFailure(String error);
    }

    public static void connectToDatabase(DatabaseCallback callback) {
        new DatabaseTask(callback).execute();
    }

    private static class DatabaseTask extends AsyncTask<Void, Void, String> {

        private DatabaseCallback callback;

        DatabaseTask(DatabaseCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                String query = "SELECT * FROM EQUIPE";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the result set or perform other database operations

                connection.close();
                return "Database connection successful";
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                callback.onSuccess(result);
            } else {
                callback.onFailure("Failed to connect to the database");
            }
        }
    }
}
