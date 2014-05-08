package hrquizer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by artem on 28.04.14.
 */
public class StartAsk extends Activity implements View.OnClickListener {

    private int id;

    private int askNumber = 0;

    public Button buttonNext;

    public int yourCredits = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        yourCredits = 0;

        ((TextView) findViewById(R.id.textView)).setText(MainMenu.workquiz.getName());

        ((RadioButton) findViewById(R.id.radio1)).setOnClickListener((View.OnClickListener) this);
        ((RadioButton) findViewById(R.id.radio2)).setOnClickListener((View.OnClickListener) this);
        ((RadioButton) findViewById(R.id.radio3)).setOnClickListener((View.OnClickListener) this);
        ((TextView) findViewById(R.id.textAsk)).setText(MainMenu.workquiz.getAsk(askNumber).getQuestion());
        ((RadioButton) findViewById(R.id.radio1)).setText(MainMenu.workquiz.getAsk(askNumber).getAnswer(0).getText()+Integer.toString(askNumber+1));
        ((RadioButton) findViewById(R.id.radio2)).setText(MainMenu.workquiz.getAsk(askNumber).getAnswer(1).getText()+Integer.toString(askNumber+1));
        ((RadioButton) findViewById(R.id.radio3)).setText(MainMenu.workquiz.getAsk(askNumber).getAnswer(2).getText()+Integer.toString(askNumber+1));

        buttonNext = (Button) findViewById(R.id.buttonNext);

    }

    public void nextQuestion(View v) {

        askNumber++;

        if (askNumber < MainMenu.workquiz.getAsks().size()) {
            ((RadioButton) findViewById(R.id.radio1)).setChecked(false);
            ((RadioButton) findViewById(R.id.radio2)).setChecked(false);
            ((RadioButton) findViewById(R.id.radio3)).setChecked(false);
            ((TextView) findViewById(R.id.textAsk)).setText(MainMenu.workquiz.getAsk(askNumber).getQuestion());
            ((RadioButton) findViewById(R.id.radio1)).setText(MainMenu.workquiz.getAsk(askNumber).getAnswer(0).getText()+Integer.toString(askNumber+1));
            ((RadioButton) findViewById(R.id.radio2)).setText(MainMenu.workquiz.getAsk(askNumber).getAnswer(1).getText()+Integer.toString(askNumber+1));
            ((RadioButton) findViewById(R.id.radio3)).setText(MainMenu.workquiz.getAsk(askNumber).getAnswer(2).getText()+Integer.toString(askNumber+1));
        } else {
            Toast.makeText(this, "Опрос окончен", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(StartAsk.this, Analysator.class);
            intent.putExtra("res", Integer.toString(yourCredits));
            startActivity(intent);
            finish();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio1:
                yourCredits = yourCredits + MainMenu.workquiz.getAsk(askNumber).getAnswer(0).getCredits();
                break;
            case R.id.radio2:
                yourCredits = yourCredits + MainMenu.workquiz.getAsk(askNumber).getAnswer(1).getCredits();
                break;
            case R.id.radio3:
                yourCredits = yourCredits + MainMenu.workquiz.getAsk(askNumber).getAnswer(2).getCredits();
                break;
            default:
                break;
        }
    }
}
