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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
* Created by Damon on 9/05/15.
* Class for displaying list contents and making / editing lists
*/

public class DisplayListActivity extends ActionBarActivity {

    ArrayList<String> listItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String listName = intent.getStringExtra("LIST_NAME");

        TextView listNameField = (TextView) findViewById(R.id.listName);
        listNameField.setText(listName);

        //TextView textView = new TextView(this);
        //textView.setTextSize(50);
        //textView.setText(listName);

        //setContentView(textView);

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


    public void addListItemName(View view) {
        // create an EditText field using xml reference
        // and save to variable listItemName
        EditText listItemNameField = (EditText) findViewById(R.id.listItemName);
        String listItemName = listItemNameField.getText().toString();

        // Add the list items to array
        listItems.add(listItemName);

        refreshList();
    }


    public void deleteListItem(View view) {

        ListView listItemsView = (ListView) findViewById(R.id.listItems);
        int indexNo = listItemsView.getPositionForView(view);

        listItems.remove(indexNo);

        refreshList();
    }


    public void refreshList() {
        // set the adapter using the instance above and refresh activity contents
        ListView listItemsView = (ListView) findViewById(R.id.listItems);
        ArrayAdapter adapters = new ArrayAdapter<String>(this, R.layout.list_item,R.id.listItemName, listItems);
        listItemsView.setAdapter(adapters);
        adapters.notifyDataSetChanged();
    }

}