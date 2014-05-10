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

import hrquizer.app.DataBaseClasses.AskDBOpenHelper;
import hrquizer.app.DataBaseClasses.QuizDBOpenHelper;

/**
 * Created by artem on 28.04.14.
 */
public class StartCodeQuiz extends Activity {

    private Quiz workQuizCode;
    private ArrayList<Ask> askArrayList = new ArrayList<Ask>();

    static final Uri CONTENT_URI_QUIZ = Uri.parse("content://hrquizer.app.DataBaseClasses.QuizDBOpenHelper/quiz");
    static final Uri CONTENT_URI_ASK = Uri.parse("content://hrquizer.app.DataBaseClasses.AskDBOpenHelper/ask");

    public static String QUIZERS = "Quizers";
    public static String QUIZ_NAME = "quizname";

    QuizAdapter quizAdapter;
    ContentResolver mContent;
    Cursor mCursor;

    public QuizCode quizCode;
    public ArrayList<QuizCode> quizCodeArrayList = new ArrayList<QuizCode>();

    public int myCode;
    public int quizID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_menu);

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

        mContent = getContentResolver();
        String[] projection = new String[] {QuizDBOpenHelper._ID ,QuizDBOpenHelper.QUIZ_NAME, QuizDBOpenHelper.CODE};
        String selection = "code = ?";
        String [] selectionArgs = new String[] { Integer.toString(myCode) };
        try {
            mCursor = mContent.query(CONTENT_URI_QUIZ, projection, selection, selectionArgs, null);
            mCursor.moveToFirst();
            do {
                workQuizCode = new Quiz(mCursor.getString(1));
                quizCode = new QuizCode(workQuizCode, Integer.parseInt(mCursor.getString(2)));
                quizCodeArrayList.add(quizCode);
                quizID = Integer.parseInt(mCursor.getString(0))-1;
                MainMenu.workquiz.setName(quizCode.getName());
                Log.e("MY_LOG_ID", Long.toString(quizID));
                //quizArrayList.add(new Quiz(mCursor.getString(2)));
                Log.e("MY_LOG", mCursor.getString(2));
                mCursor.moveToNext();
            } while (!mCursor.isAfterLast());
            mCursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "Опроса с данным кодом не существует", Toast.LENGTH_SHORT).show();
            finish();
        }
        //Log.e("MY_LOG", Integer.toString(mCursor.getColumnCount()));


        Cursor cursor;

        mContent = getContentResolver();
        projection = new String[] {AskDBOpenHelper.ASK, AskDBOpenHelper.ANSWER_1, AskDBOpenHelper.ANSWER_2, AskDBOpenHelper.ANSWER_3,
                AskDBOpenHelper.CREDITS_1, AskDBOpenHelper.CREDITS_2, AskDBOpenHelper.CREDITS_3, AskDBOpenHelper.QUIZ_ID};
        selection = "quiz_id = ?";
        selectionArgs = new String[] { Integer.toString(quizID) };
        try {
            cursor = mContent.query(CONTENT_URI_ASK, null, selection, selectionArgs, null);
            cursor.moveToFirst();
            do {
                ArrayList<Answer> answerArrayList = new ArrayList<Answer>();
                answerArrayList.add(new Answer(cursor.getString(2), Integer.parseInt(cursor.getString(5))));
                answerArrayList.add(new Answer(cursor.getString(3), Integer.parseInt(cursor.getString(6))));
                answerArrayList.add(new Answer(cursor.getString(4), Integer.parseInt(cursor.getString(7))));
                askArrayList.add(new Ask(cursor.getString(1), answerArrayList));
                Log.e("MY_LOG", cursor.getString(0) + cursor.getString(1) + cursor.getString(2) + cursor.getString(3) + cursor.getString(4) + cursor.getString(7) + cursor.getString(8));
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
            MainMenu.workquiz.setAsks(askArrayList);
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "Опроса с данным кодом не существует", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (myCode < 0) {
            throw new MinusInCodeException();
        }

    }
}
