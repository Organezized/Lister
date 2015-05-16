package com.organezized.lister;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
* Created by Damon on 9/05/15.
*/

public class DisplayListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_displaymessage);
        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String listName = intent.getStringExtra("LIST_NAME");
        TextView textView = new TextView(this);
        textView.setTextSize(50);
        textView.setText(listName);

        setContentView(textView);

        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
        */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_list,
                    container, false);
            return rootView;
        }
    }

    /**
     * Method for adding a Text Editing box
     * for new items to be added to a particular list
     */
    public static class
}