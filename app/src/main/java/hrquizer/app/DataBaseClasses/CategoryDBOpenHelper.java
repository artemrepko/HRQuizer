package hrquizer.app.DataBaseClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by artem on 06.05.14.
 */
public class CategoryDBOpenHelper extends SQLiteOpenHelper implements BaseColumns {

    public static int VERSION = 1;
    public static String DB_NAME = "hrquizer_category.db";

    public static String CATEGORIES = "Categories";
    public static String CATEGORY_NAME = "categoryname";

    public static String CATEGORY_DB_CREATED = "CREATE TABLE Categories (_id INTEGER PRIMARY KEY AUTOINCREMENT, categoryname TEXT);";

    public static int CATEGORY_COUNT = 2;

    public CategoryDBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CATEGORY_DB_CREATED);

        ContentValues values = new ContentValues();

        for (int i=0; i<CATEGORY_COUNT; i++) {
            values.put(CATEGORY_NAME, "Category " + Integer.toString(i+1));
            db.insert(CATEGORIES, CATEGORY_NAME, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + CATEGORIES);
        onCreate(db);
    }
}
