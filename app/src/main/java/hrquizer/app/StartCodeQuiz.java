package hrquizer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by artem on 28.04.14.
 */
public class StartCodeQuiz extends Activity {

    public QuizCode quizCode = new QuizCode(MainMenu.workquiz,12345);
    public ArrayList<QuizCode> quizCodeArrayList = new ArrayList<QuizCode>();

    public int myCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_menu);

        quizCodeArrayList.add(quizCode);

    }

    public void startQuizCode(View v) {

        try {
            readCode();
        } catch (MinusInCodeException e) {
            Toast.makeText(this, "Неверный формат кода", Toast.LENGTH_SHORT).show();
        }

        for (int i=0; i<quizCodeArrayList.size(); i++) {

            if (myCode == quizCodeArrayList.get(i).getCode()) {
                Intent intent = new Intent(StartCodeQuiz.this, StartAsk.class);
                startActivity(intent);
                finish();
            } else {
                if (i == quizCodeArrayList.size()-1)
                    Toast.makeText(this, "Опроса с данным кодом не существует", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void readCode() throws MinusInCodeException {
        EditText editText = (EditText) findViewById(R.id.textCode);
        String textCode = editText.getText().toString();
        myCode = Integer.parseInt(textCode);
        if (myCode < 0) {
            throw new MinusInCodeException();
        }
    }
}
