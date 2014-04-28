package hrquizer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by artem on 28.04.14.
 */
public class StartCodeQuiz extends Activity {

    public QuizCode quizCode = new QuizCode(MainMenu.workquiz,12345);
    public ArrayList<QuizCode> quizCodeArrayList = new ArrayList<QuizCode>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_menu);

        quizCodeArrayList.add(quizCode);

    }

    public void startQuizCode(View v) {

        EditText editText = (EditText) findViewById(R.id.textCode);
        String textCode = editText.getText().toString();
        int myCode = Integer.parseInt(textCode);

        for (int i=0; i<quizCodeArrayList.size(); i++) {

            if (myCode == quizCodeArrayList.get(i).getCode()) {
                Intent intent = new Intent(StartCodeQuiz.this, StartAsk.class);
                startActivity(intent);
                finish();
            }

        }
    }
}
