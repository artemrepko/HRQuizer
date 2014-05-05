package hrquizer.app;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by artem on 04.05.14.
 */
public class DBHelperProvider extends ContentProvider {

    static final Uri CONTENT_URI = Uri.parse("content://hrquizer.app/quizerdb");

    public static String CATEGORYIES = "Categories";
    public static String CATEGORY_NAME = "categoryname";

    public static String QUIZERS = "Quizers";
    public static String QUIZ_NAME = "name";
    public static String CODE = "code";
    public static String CATEGORY_ID = "category_id";
    public static String USER_ID = "user_id";
    public static String TYPEREPORT = "typereport";

    public static String ASKS = "Asks";
    public static String ASK = "Ask";
    public static String ANSWER_1 = "answer_1";
    public static String ANSWER_2 = "answer_2";
    public static String ANSWER_3 = "answer_3";
    public static String CREDITS_1 = "credits_1";
    public static String CREDITS_2 = "credits_2";
    public static String CREDITS_3 = "credits_3";
    public static String QUIZER_ID = "quizer_id";

    public static String USERS = "Users";
    public static String LOGIN = "login";
    public static String PASSWORD = "password";

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        db = new DBHelper(getContext()).getReadableDatabase();
        return (db == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String orderBy;

        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = DBHelper.CATEGORY_NAME;
        } else {
            orderBy = sortOrder;
        }

        Cursor c = db.query(DBHelper.CATEGORYIES, projection, selection, selectionArgs, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
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
