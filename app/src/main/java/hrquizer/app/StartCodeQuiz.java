package hrquizer.app;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import hrquizer.app.DataBaseClasses.QuizDBOpenHelper;

/**
 * Created by artem on 28.04.14.
 */
public class StartCodeQuiz extends Activity {

    private ArrayList<Quiz> quizArrayList = new ArrayList<Quiz>();
    private ArrayList<Ask> askArrayList = new ArrayList<Ask>();

    static final Uri CONTENT_URI_QUIZ = Uri.parse("content://hrquizer.app.DataBaseClasses.QuizDBOpenHelper/quiz");

    public static String QUIZERS = "Quizers";
    public static String QUIZ_NAME = "quizname";

    QuizAdapter quizAdapter;
    ContentResolver mContent;
    Cursor mCursor;

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

        int userCode;

        try {
            readCode();
        } catch (MinusInCodeException e) {
            Toast.makeText(this, "Неверный формат кода", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        for (int i=0; i<quizCodeArrayList.size(); i++) {

            try {
                userCode = quizCodeArrayList.get(i).getCode();
            } catch (Exception e) {
                Toast.makeText(this, "Опроса с данным кодом не существует", Toast.LENGTH_SHORT).show();
                userCode = -1;
            }

            if (myCode == userCode) {
                Intent intent = new Intent(StartCodeQuiz.this, StartAsk.class);
                startActivity(intent);
                finish();
            } else {
                if (i == quizCodeArrayList.size()-1) {
                    Toast.makeText(this, "Опроса с данным кодом не существует", Toast.LENGTH_SHORT).show();
                    finish();
                }
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

        /*mContent = getContentResolver();
        String[] projection = new String[] {QuizDBOpenHelper.QUIZ_NAME};
        String selection = "category_id = ?";
        String [] selectionArgs = new String[] { Integer.toString(myCode) };
        mCursor = mContent.query(CONTENT_URI_QUIZ, projection, selection, selectionArgs, null);
        //Log.e("MY_LOG", Integer.toString(mCursor.getColumnCount()));
        mCursor.moveToFirst();

        do {
            quizArrayList.add(new Quiz(mCursor.getString(0)));
            Log.e("MY_LOG", mCursor.getString(0));
            mCursor.moveToNext();
        } while (!mCursor.isAfterLast());
        mCursor.close();*/


    }
}
