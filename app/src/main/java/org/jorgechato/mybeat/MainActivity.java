package org.jorgechato.mybeat;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import org.jorgechato.mybeat.database.Database;
import org.jorgechato.mybeat.fragments.ControlFragment;
import org.jorgechato.mybeat.fragments.ProfileFragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {
    private static Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        database = new Database(this);

        startFragment();
    }

    public static Database getDatabase() {
        return database;
    }

    private void tabSwipe(ActionBar actionBar) {
        final ActionBar mViewPager = actionBar;

    }

    public void startFragment(){
        Resources resources = getResources();
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tabProfile= actionBar.newTab().setText(resources.getString(R.string.tab_profile));
        ActionBar.Tab tabControl= actionBar.newTab().setText(resources.getString(R.string.tab_control));

        Fragment fragmentProfile = new ProfileFragment();
        Fragment fragmentControl = new ControlFragment();

        tabProfile.setTabListener(new TabsListener(fragmentProfile));
        tabControl.setTabListener(new TabsListener(fragmentControl));

        actionBar.addTab(tabProfile,false);
        actionBar.addTab(tabControl,true);

        tabSwipe(actionBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_acercade) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        Intent intent = new Intent(this, AddControl.class);
        startActivity(intent);
    }
}
