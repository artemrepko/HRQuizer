package hrquizer.app.DataBaseClasses;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import hrquizer.app.CategoryAdapter;
import hrquizer.app.QuizAdapter;

/**
 * Created by artem on 06.05.14.
 */
public class QuizDBProvider extends ContentProvider {

    static final Uri CONTENT_URI = Uri.parse("content://hrquizer.app.DataBaseClasses/quizerdb");

    public static String QUIZERS = "Quizers";
    public static String QUIZ_NAME = "quizname";

    SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {
        sqLiteDatabase = new QuizDBOpenHelper(getContext()).getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String orderBy = QUIZ_NAME;

        Cursor cursor = sqLiteDatabase.query(QuizDBOpenHelper.QUIZERS, projection, selection, selectionArgs, null, null, orderBy);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
