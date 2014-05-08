package hrquizer.app.DataBaseClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by artem on 06.05.14.
 */
public class AskDBOpenHelper extends SQLiteOpenHelper implements BaseColumns {

    public static int VERSION = 1;
    public static String DB_NAME = "hrquizer_asks.db";

    public static String ASKS = "Asks";
    public static String ASK = "ask";
    public static String ANSWER_1 = "answer_1";
    public static String ANSWER_2 = "answer_2";
    public static String ANSWER_3 = "answer_3";
    public static String CREDITS_1 = "credits_1";
    public static String CREDITS_2 = "credits_2";
    public static String CREDITS_3 = "credits_3";
    public static String QUIZ_ID = "quiz_id";

    public static String ASK_DB_CREATED = "CREATE TABLE Asks (_id INTEGER PRIMARY KEY AUTOINCREMENT, ask TEXT, answer_1 TEXT, answer_2 TEXT, answer_3 TEXT, credits_1 INTEGER, credits_2 INTEGER, credits_3 INTEGER, quiz_id INTEGER);";

    public static int ASK_COUNT = 3;

    public int myid = 0;

    public AskDBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ASK_DB_CREATED);

        ContentValues values = new ContentValues();

        for (int i=0; i < CategoryDBOpenHelper.CATEGORY_COUNT; i++) {
            for (int j=0; j < QuizDBOpenHelper.QUIZ_COUNT; j++) {
                for (int k=0; k < ASK_COUNT; k++) {
                    values.put(ASK, "Question " + Integer.toString(k+1) + " Quiz " + Integer.toString(j+1));
                    values.put(ANSWER_1, "Ansver " + Integer.toString(k+1) + ".1");
                    values.put(ANSWER_2, "Ansver " + Integer.toString(k+1) + ".2");
                    values.put(ANSWER_3, "Ansver " + Integer.toString(k+1) + ".3");
                    values.put(CREDITS_1, 1);
                    values.put(CREDITS_2, 2);
                    values.put(CREDITS_3, 3);
                    values.put(QUIZ_ID, myid);
                    db.insert(ASKS, null, values);
                    Log.e("MY_LOG_ID", Integer.toString(myid));
                }
                myid++;
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + ASKS);
        onCreate(db);
    }
}
