package gt.org.kinal.userprofile.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import gt.org.kinal.userprofile.bean.Order;
import gt.org.kinal.userprofile.sqlite.DBOpenHelper;
import gt.org.kinal.userprofile.sqlite.SQLiteTable;

/**
 * Created by Alejo on 21/03/2015.
 */
public class OrderModel {

    private SQLiteOpenHelper helper;
    private SQLiteTable table;

    public OrderModel(Context context) {
        helper = new DBOpenHelper(context);
        table = ((DBOpenHelper)helper).searchTable("orders");
    }

    public void add(Order order) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues record = new ContentValues();
        //record.put(table.TABLE_COLUMNS[0], order.getIdOrder());
        record.put(table.TABLE_COLUMNS[1], order.getMainDish());
        record.put(table.TABLE_COLUMNS[2], order.getSideDish1());
        record.put(table.TABLE_COLUMNS[3], order.getSideDish2());
        record.put(table.TABLE_COLUMNS[4], order.hasSoup());
        record.put(table.TABLE_COLUMNS[5], order.getDessert());
        database.insert(table.TABLE_NAME, null, record);
        database.close();
    }

    public ArrayList<Order> getData() {
        ArrayList<Order> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query(table.TABLE_NAME, table.TABLE_COLUMNS, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new Order(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4) == 1, cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }
}
