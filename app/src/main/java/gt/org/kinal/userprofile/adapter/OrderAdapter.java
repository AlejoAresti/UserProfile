package gt.org.kinal.userprofile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import gt.org.kinal.userprofile.R;
import gt.org.kinal.userprofile.bean.Order;

/**
 * Created by Alejo on 21/03/2015.
 */
public class OrderAdapter extends ArrayAdapter<Order> {

    private String[] mainDishes, sideDishes;

    private Order[] datos;

    public OrderAdapter(Context context, Order[] datos) {
        super(context, R.layout.listitem_order, datos);
        this.datos = datos;
        mainDishes = context.getResources().getStringArray(R.array.main_dish);
        sideDishes = context.getResources().getStringArray(R.array.side_dish);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listitem_order, null);
        ((TextView)item.findViewById(R.id.lblOrderID)).setText(String.valueOf(datos[position].getIdOrder()));
        ((TextView)item.findViewById(R.id.lblMainDish)).setText(mainDishes[datos[position].getMainDish()]);
        ((TextView)item.findViewById(R.id.lblSideDish1)).setText(sideDishes[datos[position].getSideDish1()]);
        ((TextView)item.findViewById(R.id.lblSideDish2)).setText(sideDishes[datos[position].getSideDish2()]);
        return item;
    }
}
