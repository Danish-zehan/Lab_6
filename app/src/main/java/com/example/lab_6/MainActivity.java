package com.example.lab_6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);

        // Start downloading cat images asynchronously
        new CatImages().execute();
    }

    // AsyncTask for downloading and displaying cat images
    private class CatImages extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap catBitmap = null;
            try {
                // Simulate downloading a random cat image (can be replaced with API call if needed)
                String imageUrl = "https://cataas.com/cat"; // Placeholder API URL
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                catBitmap = BitmapFactory.decodeStream(inputStream);

                // Simulate a delay while downloading
                for (int i = 0; i <= 100; i++) {
                    publishProgress(i);
                    Thread.sleep(30);  // Adjust time as needed
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return catBitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                imageView.setImageBitmap(result);  // Update ImageView with downloaded image
            }
        }
    }
}
