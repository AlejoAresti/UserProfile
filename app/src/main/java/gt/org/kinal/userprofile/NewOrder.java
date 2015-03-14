package gt.org.kinal.userprofile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;


public class NewOrder extends ActionBarActivity {

    String[] mainDishes, sideDishes, desserts;

    private int sideDishesSelected;

    private RadioButton rbMeat, rbChicken, rbFish, rbNoDessert, rbIceCream, rbPie;
    private CheckBox[] cboxSideDishes;
    private Spinner cboxBeverages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        //Initialize the TabHost
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
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

        tabHost.setCurrentTab(0);

        // MENU //
        //Sets the values of the main dishes
        mainDishes = getResources().getStringArray(R.array.main_dish);
        rbMeat = (RadioButton)findViewById(R.id.rbMeat);
        rbChicken = (RadioButton)findViewById(R.id.rbChicken);
        rbFish = (RadioButton)findViewById(R.id.rbFish);
        rbMeat.setText(mainDishes[MainDish.MEAT.ordinal()]);
        rbChicken.setText(mainDishes[MainDish.CHICKEN.ordinal()]);
        rbFish.setText(mainDishes[MainDish.FISH.ordinal()]);
        //Default
        rbMeat.setSelected(true);

        //Sets the values of the side dishes
        sideDishes = getResources().getStringArray(R.array.side_dish);
        cboxSideDishes = new CheckBox[4];

        cboxSideDishes[0] = (CheckBox)findViewById(R.id.cbPotatoes);
        cboxSideDishes[1] = (CheckBox)findViewById(R.id.cbRice);
        cboxSideDishes[2] = (CheckBox)findViewById(R.id.cbNoodles);
        cboxSideDishes[3] = (CheckBox)findViewById(R.id.cbSalad);

        //Listener for the side dishes checkboxes (there can only be 2 selected)
        SideDishesCheckBoxListener sdcbl = new SideDishesCheckBoxListener();
        for (SideDish sideDish : SideDish.values()) {
            cboxSideDishes[sideDish.ordinal()].setText(sideDishes[sideDish.ordinal()]);
            cboxSideDishes[sideDish.ordinal()].setOnCheckedChangeListener(sdcbl);
        }

        //Spinner of Beverages
        ArrayAdapter<CharSequence> beveragesAdapter = ArrayAdapter.createFromResource(this, R.array.beverages, android.R.layout.simple_spinner_item);
        cboxBeverages = (Spinner)findViewById(R.id.cboxBeverages);
        cboxBeverages.setAdapter(beveragesAdapter);


        // EXTRAS //
        //Sets the values of the main dishes
        desserts = getResources().getStringArray(R.array.desserts);
        rbNoDessert = (RadioButton)findViewById(R.id.rbNoDessert);
        rbIceCream = (RadioButton)findViewById(R.id.rbIceCream);
        rbPie = (RadioButton)findViewById(R.id.rbPie);
        rbNoDessert.setText(desserts[MainDish.MEAT.ordinal()]);
        rbIceCream.setText(desserts[MainDish.CHICKEN.ordinal()]);
        rbPie.setText(desserts[MainDish.FISH.ordinal()]);
        //Default
        rbNoDessert.setSelected(true);

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
    //Enable and Disable the check box of the unchecked side dishes
    public void sideDishesEnabled(boolean enabled) {
        for (CheckBox sideDish : cboxSideDishes) {
            if (!sideDish.isChecked()) {
                sideDish.setEnabled(enabled);
            }
        }
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
