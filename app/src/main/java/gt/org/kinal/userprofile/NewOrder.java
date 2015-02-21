package gt.org.kinal.userprofile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class NewOrder extends ActionBarActivity {

    String[] mainDishes, sideDishes;

    private RadioGroup rgMainDishes;
    private RadioButton rbMeat, rbChicken, rbFish;
    private CheckBox cbPotatoes, cbRice, cbNoodles, cbSalad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        //Sets the values of the main dishes
        mainDishes = getResources().getStringArray(R.array.main_dish);
        rgMainDishes = (RadioGroup)findViewById(R.id.rgMainDishes);
        rbMeat = (RadioButton)findViewById(R.id.rbMeat);
        rbChicken = (RadioButton)findViewById(R.id.rbChicken);
        rbFish = (RadioButton)findViewById(R.id.rbFish);
        rbMeat.setText(mainDishes[MainDishes.MEAT]);
        rbChicken.setText(mainDishes[MainDishes.CHICKEN]);
        rbFish.setText(mainDishes[MainDishes.FISH]);

        //Sets the values of the side dishes
        sideDishes = getResources().getStringArray(R.array.side_dish);
        cbPotatoes = (CheckBox)findViewById(R.id.cbPotatoes);
        cbRice = (CheckBox)findViewById(R.id.cbRice);
        cbNoodles = (CheckBox)findViewById(R.id.cbNoodles);
        cbSalad = (CheckBox)findViewById(R.id.cbSalad);
        cbPotatoes.setText(sideDishes[0]);
        cbRice.setText(sideDishes[1]);
        cbNoodles.setText(sideDishes[2]);
        cbSalad.setText(sideDishes[3]);
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
}
