package hrquizer.app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hrquizer.app.DataBaseClasses.AskDBOpenHelper;
import hrquizer.app.DataBaseClasses.CategoryDBOpenHelper;
import hrquizer.app.DataBaseClasses.QuizDBOpenHelper;

public class StartQuizer extends Activity implements AdapterView.OnItemClickListener {

    static final Uri CONTENT_URI_QUIZ = Uri.parse("content://hrquizer.app.DataBaseClasses.QuizDBOpenHelper/quiz");
    static final Uri CONTENT_URI_ASK = Uri.parse("content://hrquizer.app.DataBaseClasses.AskDBOpenHelper/ask");

    public static String QUIZERS = "Quizers";
    public static String QUIZ_NAME = "quizname";

    private ArrayList<Quiz> quizArrayList = new ArrayList<Quiz>();
    private ArrayList<Ask> askArrayList = new ArrayList<Ask>();
    private ArrayList<Answer> answerArrayList = new ArrayList<Answer>();

    public String categoryName;
    public int categoryId;
    public int quiz_count=0;

    QuizAdapter quizAdapter;
    ContentResolver mContent;
    Cursor mCursor;

    public int quizID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quizer);

        categoryName = getIntent().getStringExtra("categoryName");
        categoryId = getIntent().getIntExtra("categoryId", 0);

        mContent = getContentResolver();
        String[] projection = new String[] {QuizDBOpenHelper.QUIZ_NAME};
        String selection = "category_id = ?";
        String [] selectionArgs = new String[] { Integer.toString(categoryId) };
        mCursor = mContent.query(CONTENT_URI_QUIZ, projection, selection, selectionArgs, null);
        //Log.e("MY_LOG", Integer.toString(mCursor.getColumnCount()));
        mCursor.moveToFirst();

        do {
            quizArrayList.add(new Quiz(mCursor.getString(0)));
            quizArrayList.get(quiz_count).setId(categoryId+quiz_count);
            Log.e("MY_LOG", mCursor.getString(0));
            mCursor.moveToNext();
            quiz_count++;
        } while (!mCursor.isAfterLast());
        mCursor.close();

        quizAdapter = new QuizAdapter(this, quizArrayList);

        ListView lvMain = (ListView) findViewById(R.id.list_view);
        lvMain.setAdapter(quizAdapter);
        lvMain.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mContent = getContentResolver();

            quizID = quizArrayList.get(position).getId()+categoryId;
            Log.e("MY_LOG_ID", Long.toString(id));

        String[] projection = new String[] {AskDBOpenHelper.ASK, AskDBOpenHelper.ANSWER_1, AskDBOpenHelper.ANSWER_2, AskDBOpenHelper.ANSWER_3,
                AskDBOpenHelper.CREDITS_1, AskDBOpenHelper.CREDITS_2, AskDBOpenHelper.CREDITS_3, AskDBOpenHelper.QUIZ_ID};
        String selection = "quiz_id = ?";
        String[] selectionArgs = new String[] { Integer.toString(quizID) };
        Cursor cursor = mContent.query(CONTENT_URI_ASK, null, selection, selectionArgs, null);
        cursor.moveToFirst();

        do {
            answerArrayList.clear();
            answerArrayList.add(new Answer(cursor.getString(2), Integer.parseInt(cursor.getString(5))));
            answerArrayList.add(new Answer(cursor.getString(3), cursor.getInt(6)));
            answerArrayList.add(new Answer(cursor.getString(4), cursor.getInt(7)));
            askArrayList.add(new Ask(cursor.getString(1), answerArrayList));
            Log.e("MY_LOG", cursor.getString(1) + cursor.getString(2) + cursor.getString(3) + cursor.getString(4) + cursor.getString(7) + cursor.getString(8));
            Log.e("ASK_READ", "Start!!!");
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        cursor.close();

        MainMenu.workquiz.setName(quizArrayList.get(position).getName());
        //MainMenu.workquiz.setId(quizArrayList.get(position).getId());
        MainMenu.workquiz.setAsks(askArrayList);

        Intent intent = new Intent(StartQuizer.this, StartAsk.class);
        startActivity(intent);
        finish();
    }
}


