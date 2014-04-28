package hrquizer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by artem on 27.04.14.
 */
public class Analysator extends Activity {

    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysator);

        result = getIntent().getStringExtra("res");

        ((TextView) findViewById(R.id.textView2)).setText("Вы набрали " + result + " очков");
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void toMainMenu(View v) {
        finish();
    }
}
