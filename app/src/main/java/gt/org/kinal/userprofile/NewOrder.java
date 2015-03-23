package gt.org.kinal.userprofile;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;

import gt.org.kinal.userprofile.bean.Order;
import gt.org.kinal.userprofile.model.OrderModel;
import gt.org.kinal.userprofile.sqlite.DBOpenHelper;
import gt.org.kinal.userprofile.sqlite.SQLiteTable;

public class NewOrder extends ActionBarActivity {

    private OrderModel orderModel;

    private String[] mainDishes, sideDishes, desserts;
    private int sideDishesSelected;

    //Order fields
    private RadioGroup rgMainDish;
    private RadioButton rbMeat, rbChicken, rbFish;
    private CheckBox[] cboxSideDishes;
    private Switch swWantSoup;
    private Spinner cboxBeverages;
    private RadioButton rbNoDessert, rbIceCream, rbPie;

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        orderModel = new OrderModel(this);

        //Initialize the TabHost
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;
        //HomeTab
        tabSpec = tabHost.newTabSpec("tabMenu");
        tabSpec.setContent(R.id.tabMenu);
        tabSpec.setIndicator(getResources().getString(R.string.menu));
        tabHost.addTab(tabSpec);
        //ProfileTab
        tabSpec = tabHost.newTabSpec("tabExtras");
        tabSpec.setContent(R.id.tabExtras);
        tabSpec.setIndicator(getResources().getString(R.string.extras));
        tabHost.addTab(tabSpec);

        //-- MENU --//
        //Sets the values of the main dishes
        mainDishes = getResources().getStringArray(R.array.main_dish);
        rgMainDish = (RadioGroup)findViewById(R.id.rgMainDishes);
        rbMeat = (RadioButton)findViewById(R.id.rbMeat);
        rbChicken = (RadioButton)findViewById(R.id.rbChicken);
        rbFish = (RadioButton)findViewById(R.id.rbFish);
        rbMeat.setText(mainDishes[0]);
        rbChicken.setText(mainDishes[1]);
        rbFish.setText(mainDishes[2]);

        //Sets the values of the side dishes
        sideDishes = getResources().getStringArray(R.array.side_dish);
        cboxSideDishes = new CheckBox[4];

        cboxSideDishes[0] = (CheckBox)findViewById(R.id.cbPotatoes);
        cboxSideDishes[1] = (CheckBox)findViewById(R.id.cbRice);
        cboxSideDishes[2] = (CheckBox)findViewById(R.id.cbNoodles);
        cboxSideDishes[3] = (CheckBox)findViewById(R.id.cbSalad);

        //Listener for the side dishes checkboxes (there can only be 2 selected)
        SideDishesCheckBoxListener sdcbl = new SideDishesCheckBoxListener();
        for (int i = 0; i < sideDishes.length; i++) {
            cboxSideDishes[i].setText(sideDishes[i]);
            cboxSideDishes[i].setOnCheckedChangeListener(sdcbl);
        }

        //Switch for Soup
        swWantSoup = (Switch)findViewById(R.id.swWantSoup);

        //Spinner of Beverages
        ArrayAdapter<CharSequence> beveragesAdapter = ArrayAdapter.createFromResource(this, R.array.beverages, android.R.layout.simple_spinner_item);
        cboxBeverages = (Spinner)findViewById(R.id.cboxBeverages);
        cboxBeverages.setAdapter(beveragesAdapter);

        //-- EXTRAS --//
        //Sets the values of the desserts
        desserts = getResources().getStringArray(R.array.desserts);
        rbNoDessert = (RadioButton)findViewById(R.id.rbNoDessert);
        rbIceCream = (RadioButton)findViewById(R.id.rbIceCream);
        rbPie = (RadioButton)findViewById(R.id.rbPie);
        rbNoDessert.setText(desserts[0]);
        rbIceCream.setText(desserts[1]);
        rbPie.setText(desserts[2]);

        //Add NewOrder Action
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order newOrder = getValidatedOrder();
                if (newOrder != null) {
                    orderModel.add(newOrder);
                }
                finish();
            }
        });

        resetNewOrder();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_order, menu);
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
    //Resets all the controls to their default value
    public void resetNewOrder() {
        tabHost.setCurrentTab(0);
        rbMeat.setChecked(true);
        for (CheckBox cboxSideDish : cboxSideDishes) {
            cboxSideDish.setChecked(false);
        }
        sideDishesEnabled(true);
        swWantSoup.setChecked(false);
        cboxBeverages.setSelection(0);
        rbNoDessert.setSelected(true);

    }
    //Enable and Disable the check box of the unchecked side dishes
    public void sideDishesEnabled(boolean enabled) {
        for (CheckBox sideDish : cboxSideDishes) {
            if (!sideDish.isChecked()) {
                sideDish.setEnabled(enabled);
            }
        }
    }
    //Validates the fields of the Order
    public Order getValidatedOrder() {
        //MainDish
        int mainDish = rgMainDish.indexOfChild(rgMainDish.findViewById(rgMainDish.getCheckedRadioButtonId()));
        if(mainDish == -1) {
            return null;
        }
        //SideDishes
        int sideDish1 = 0;
        int sideDish2 = 0;
        if(sideDishesSelected < 2) {
            return null;
        }
        for (int i = 0; i < cboxSideDishes.length; i++) {
            if (cboxSideDishes[i].isChecked()) {
                if(sideDish1 == 0) {
                    sideDish1 = i;
                } else {
                    sideDish2 = i;
                }
            }
        }
        return new Order(0, mainDish, sideDish1, sideDish2, swWantSoup.isChecked(), cboxBeverages.getSelectedItemPosition());
    }

    //Side dishes checkbox listener
    private class SideDishesCheckBoxListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            sideDishesSelected += isChecked ? 1 : -1;
            sideDishesEnabled(NewOrder.this.sideDishesSelected < 2);
        }
    }
}
