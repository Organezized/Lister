package com.organezized.lister;

import android.content.Intent;
import android.renderscript.ScriptIntrinsicLUT;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;


public class MainListActivity extends ActionBarActivity {
    //public final static String EXTRA_MESSAGE = "Message";

    ArrayList<String> loadedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        getMenuInflater().inflate(R.menu.menu_main_list, menu);
        return super.onCreateOptionsMenu(menu);
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


    public void newList(View view) {

        // First, grab the name of the new list from the view.
        EditText editText = (EditText) findViewById(R.id.editText);
        String listName = editText.getText().toString();

        // Add the new List.
        loadedItems.add(listName);

        // Make a reference to the Lists.
        ListView lists = (ListView) findViewById(R.id.lists);

        // Create an array adapter (for some reason) ?? Not sure why yet.
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_list_item,R.id.listName, loadedItems);
        lists.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void editList(View view) {
        TextView textView = (TextView) findViewById(R.id.listName);

        String listName = textView.getText().toString();
        Intent intent = new Intent(this, DisplayListActivity.class);
        intent.putExtra("LIST_NAME", listName);
        startActivity(intent);
    }
}