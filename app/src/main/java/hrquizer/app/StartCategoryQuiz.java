package hrquizer.app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import hrquizer.app.DataBaseClasses.CategoryDBProvider;

/**
 * Created by artem on 28.04.14.
 */
public class StartCategoryQuiz extends Activity implements AdapterView.OnItemClickListener {

    static final Uri CONTENT_URI = Uri.parse("content://hrquizer.app.DataBaseClasses/quizerdb");

    public static String CATEGORYIES = "Categories";
    public static String CATEGORY_NAME = "categoryname";

    public ArrayList<Category> categoryArrayList = new ArrayList<Category>();

    SQLiteDatabase db;
    CategoryAdapter categoryAdapter;
    ContentResolver mContent;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_menu);

        mContent = this.getContentResolver();
        final String[] projection = new String[] {CATEGORY_NAME};
        mCursor = mContent.query(CONTENT_URI, projection, null, null, null);
        mCursor.moveToFirst();

        do {
            categoryArrayList.add(new Category(mCursor.getString(0),mCursor.getPosition()));
            Log.e("MY_LOG", mCursor.getString(0));
            Log.e("MY_LOG", Integer.toString(mCursor.getPosition()));
            mCursor.moveToNext();
        } while (!mCursor.isAfterLast());

        categoryAdapter = new CategoryAdapter(this, categoryArrayList);
        ListView categoryList = (ListView) findViewById(R.id.list_view_category);
        categoryList.setAdapter(categoryAdapter);
        categoryList.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(StartCategoryQuiz.this, StartQuizer.class);
        intent.putExtra("categoryName",categoryArrayList.get(position).getName());
        intent.putExtra("categoryId",categoryArrayList.get(position).getId());
        startActivity(intent);
        finish();
    }
}
