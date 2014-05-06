package hrquizer.app;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by artem on 28.04.14.
 */
public class StartApp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

    }

    public void StartMyApp(View v) {
        connectToServer();
        Intent intent = new Intent(StartApp.this, Authorization.class);
        startActivity(intent);
        finish();
    }

    public void connectToServer() {
        URL url = null;
        try {
            url = new URL("http://example.com");
        } catch (MalformedURLException e) {}

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            Toast.makeText(this, "Ошибка подключения", Toast.LENGTH_SHORT).show();
        }

        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (Exception e) {
            Toast.makeText(this, "Не удалось подключится к серверу", Toast.LENGTH_SHORT).show();
        } finally {
            urlConnection.disconnect();
        }
    }

}
