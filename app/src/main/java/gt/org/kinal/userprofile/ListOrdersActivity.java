package gt.org.kinal.userprofile;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import gt.org.kinal.userprofile.adapter.OrderAdapter;
import gt.org.kinal.userprofile.bean.Order;
import gt.org.kinal.userprofile.model.OrderModel;
import gt.org.kinal.userprofile.sqlite.DBOpenHelper;
import gt.org.kinal.userprofile.sqlite.SQLiteTable;


public class ListOrdersActivity extends ActionBarActivity {

    private ListView listOrders;
    private OrderModel orderModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);
        orderModel = new OrderModel(this);

        listOrders = (ListView)findViewById(R.id.listOrders);
        ArrayList<Order> data = orderModel.getData();
        listOrders.setAdapter(new OrderAdapter(this, data.toArray(new Order[data.size()])));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_orders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
