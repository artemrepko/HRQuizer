package hrquizer.app.DataBaseClasses;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by artem on 06.05.14.
 */
public class AskDBProvider extends ContentProvider {

    public static String ASKS = "Asks";
    public static String ASK = "ask";
    public static String ANSWER_1 = "answer_1";
    public static String ANSWER_2 = "answer_2";
    public static String ANSWER_3 = "answer_3";
    public static String CREDITS_1 = "credits_1";
    public static String CREDITS_2 = "credits_2";
    public static String CREDITS_3 = "credits_3";
    public static String QUIZER_ID = "quizer_id";

    SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {
        sqLiteDatabase = new AskDBOpenHelper(getContext()).getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String orderBy = ASK;

        Cursor cursor = sqLiteDatabase.query(AskDBOpenHelper.ASKS, projection, selection, selectionArgs, null, null, orderBy);
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
