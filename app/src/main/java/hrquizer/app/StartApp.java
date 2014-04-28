package hrquizer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        Intent intent = new Intent(StartApp.this, Authorization.class);
        startActivity(intent);
        finish();
    }

}
