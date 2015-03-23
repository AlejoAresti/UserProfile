package gt.org.kinal.userprofile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Alejo on 21/03/2015.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static ArrayList<SQLiteTable> tables;

    public DBOpenHelper(Context context) {
        super(context, "restaurant", null, 1);
        tables = new ArrayList<>();
        addTable(new SQLiteTable("orders",
                new String[] {"idOrder", "mainDish", "sideDish1", "sideDish2", "soup", "dessert"},
                new String[] {"integer autoincrement", "integer not null", "integer not null", "integer not null", "integer not null", "integer"},
                new String[] {"idOrder"}
        ));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (SQLiteTable element : tables) {
            db.execSQL(element.getCreateStatement());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (SQLiteTable element : tables) {
            db.execSQL(element.getDeleteStatement());
        }
        onCreate(db);
    }

    public SQLiteTable searchTable(String tableName) {
        SQLiteTable found = null;
        for (SQLiteTable element: tables) {
            if (element.TABLE_NAME.equalsIgnoreCase(tableName)) {
                found = element;
                break;
            }
        }
        return found;
    }

    private void addTable(SQLiteTable table) {
        tables.add(table);
    }

}
