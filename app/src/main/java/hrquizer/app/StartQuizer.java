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

import hrquizer.app.DataBaseClasses.QuizDBOpenHelper;

public class StartQuizer extends Activity implements AdapterView.OnItemClickListener {

    static final Uri CONTENT_URI = Uri.parse("content://hrquizer.app.DataBaseClasses.QuizDBOpenHelper/quiz");

    public static String QUIZERS = "Quizers";
    public static String QUIZ_NAME = "quizname";

    private ArrayList<Quiz> quizArrayList = new ArrayList<Quiz>();

    public String categoryName;
    public int categoryId;

    QuizAdapter quizAdapter;
    ContentResolver mContent;
    Cursor mCursor;

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
        mCursor = mContent.query(CONTENT_URI, projection, selection, selectionArgs, null);
        Log.e("MY_LOG", Integer.toString(mCursor.getColumnCount()));
        mCursor.moveToFirst();

        do {
            quizArrayList.add(new Quiz(mCursor.getString(0)));
            Log.e("MY_LOG", mCursor.getString(0));
            mCursor.moveToNext();
        } while (!mCursor.isAfterLast());
        mCursor.close();

        quizAdapter = new QuizAdapter(this, quizArrayList);

        ListView lvMain = (ListView) findViewById(R.id.list_view);
        lvMain.setAdapter(quizAdapter);
        lvMain.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(StartQuizer.this, StartAsk.class);
        startActivity(intent);
        finish();
    }
}


