package hrquizer.app.DataBaseClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by artem on 06.05.14.
 */
public class QuizDBOpenHelper extends SQLiteOpenHelper implements BaseColumns {

    public static int VERSION = 1;
    public static String DB_NAME = "hrquizer_quiz.db";

    public static String QUIZERS = "Quizers";
    public static String QUIZ_NAME = "quizname";
    public static String CODE = "code";
    public static String CATEGORY_ID = "category_id";
    public static String USER_ID = "user_id";
    public static String TYPEREPORT = "typereport";

    public static String QUIZ_DB_CREATED = "CREATE TABLE Quizers (_id INTEGER PRIMARY KEY AUTOINCREMENT, quizname TEXT, code INTEGER, category_id INTEGER, user_id INTEGER, typereport TEXT);";

    public static int QUIZ_COUNT = 2;

    public int code_count=0;

    public QuizDBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUIZ_DB_CREATED);

        for (int i=0; i<CategoryDBOpenHelper.CATEGORY_COUNT; i++) {
            for (int j = 0; j < QUIZ_COUNT; j++) {
                ContentValues values = new ContentValues();
                values.put(QUIZ_NAME, "Quiz " + Integer.toString(j + 1) + " Category " + Integer.toString(i+1));
                values.put(CODE, code_count);
                values.put(CATEGORY_ID, i);
                db.insert(QUIZERS, null, values);
                code_count++;
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + QUIZERS);
        onCreate(db);
    }
}
