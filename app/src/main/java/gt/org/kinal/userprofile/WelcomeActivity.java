package gt.org.kinal.userprofile;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;


public class WelcomeActivity extends ActionBarActivity {

    private TextView lblWelcome;
    private Button btnNewOrder, btnOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Bundle bundle = this.getIntent().getExtras();
        lblWelcome = (TextView)findViewById(R.id.lblWelcome);
        lblWelcome.setText(getString(R.string.welcome) + " " + bundle.getString("user"));

        btnNewOrder = (Button)findViewById(R.id.btnNewOrder);
        btnNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, NewOrder.class));
            }
        });

        btnOrders = (Button)findViewById(R.id.btnOrders);
        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, ListOrdersActivity.class));
            }
        });

        //Resources from android
        Resources resources = getResources();

        //Initialize the TabHost
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;
        //HomeTab
        tabSpec = tabHost.newTabSpec("tabHome");
        tabSpec.setContent(R.id.tabHome);
        tabSpec.setIndicator(resources.getString(R.string.home));
        tabHost.addTab(tabSpec);
        //ProfileTab
        tabSpec = tabHost.newTabSpec("tabProfile");
        tabSpec.setContent(R.id.tabProfile);
        tabSpec.setIndicator(resources.getString(R.string.profile));
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
