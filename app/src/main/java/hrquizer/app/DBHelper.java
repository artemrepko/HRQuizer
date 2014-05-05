package hrquizer.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by artem on 04.05.14.
 */
public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    public static int VERSION = 1;
    public static String DB_NAME = "hrquizerdb.db";

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

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Categories (_id INTEGER PRIMARY KEY, categoryname TEXT);");
        db.execSQL("CREATE TABLE Quizers (\n" +
                "  _id INTEGER PRIMARY KEY,\n" +
                "  name TEXT,\n" +
                "  code INTEGER,\n" +
                "  category_id INTEGER,\n" +
                "  user_id INTEGER,\n" +
                "  typereport BLOB\n" +
                ");");
        db.execSQL("CREATE TABLE Asks (\n" +
                "  _id INTEGER PRIMARY KEY,\n" +
                "  ask TEXT,\n" +
                "  answer_1 TEXT,\n" +
                "  answer_2 TEXT,\n" +
                "  answer_3 TEXT,\n" +
                "  credits_1 INTEGER,\n" +
                "  credits_2 INTEGER,\n" +
                "  credits_3 INTEGER,\n" +
                "  quizer_id INTEGER\n" +
                ");");
        db.execSQL("CREATE TABLE Users (\n" +
                "  _id INTEGER PRIMARY KEY,\n" +
                "  login TEXT,\n" +
                "  password TEXT\n" +
                ");");

        ContentValues values = new ContentValues();

        for (int i=0; i<5; i++) {
            values.put("categoryname","Category " + Integer.toString(i+1));
            db.insert("Categories","categoryname",values);
        }

        for (int i=0; i<5; i++) {
            values.clear();
            for (int j = 0; j < 3; j++) {
                values.put("name", "Quiz " + Integer.toString(j + 1) + "category");
                values.put("typereport", 0);
                values.put("category_id", 1);
                db.insert("Quizers", "name", values);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
