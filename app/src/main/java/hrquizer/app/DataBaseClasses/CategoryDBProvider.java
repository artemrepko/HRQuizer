package hrquizer.app.DataBaseClasses;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by artem on 06.05.14.
 */
public class CategoryDBProvider extends ContentProvider {

    public static String CATEGORIES = "Categories";
    public static String CATEGORY_NAME = "categoryname";

    SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {
        sqLiteDatabase = new CategoryDBOpenHelper(getContext()).getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String orderBy = CATEGORY_NAME;

        Cursor cursor = sqLiteDatabase.query(CATEGORIES, projection, selection, selectionArgs, null, null, orderBy);
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
