package com.organezized.lister;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
/**
 * Class for main page: create list main page
 */
public class MainListActivity extends ActionBarActivity {

    // Array stores the shopping list names
    ArrayList<String> shoppingListNames = new ArrayList<>();


    // load list items and populate list name page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        loadItems();
        refreshList();
    }


    // Adds items to action bar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        getMenuInflater().inflate(R.menu.menu_main_list, menu);
        return super.onCreateOptionsMenu(menu);
    }


    // Handles Action bar items
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


    // Method creates a new item on the list (list name : edit)
    public void newList(View view) {

        // First, grab the name of the new list from the view.
        EditText editText = (EditText) findViewById(R.id.editText);
        String listName = editText.getText().toString();

        // Add the new List.
        shoppingListNames.add(listName);

        // Make a reference to the Lists.
        ListView lists = (ListView) findViewById(R.id.lists);

        // Create an array adapter (for some reason) ?? Not sure why yet.
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_list_item,R.id.listName, shoppingListNames);
        lists.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // Save the list names to file
        saveItems();
    }


    // On click handler(when edit is clicked)
    // pass list name to the page: where you edit the list
    public void editList(View view) {
        // get parent of button
        LinearLayout buttonParent = (LinearLayout) view.getParent();
        TextView textView =  (TextView) buttonParent.getChildAt(0);

        String listName = textView.getText().toString();

        Intent intent = new Intent(this, DisplayListActivity.class);
        intent.putExtra("LIST_NAME", listName);
        startActivity(intent);

        refreshList();
    }


    // refresh list after making changes
    public void refreshList() {
        // set the adapter using the instance above and refresh activity contents
        ListView listItemsView = (ListView) findViewById(R.id.lists);
        ArrayAdapter adapters = new ArrayAdapter<String>(this, R.layout.activity_list_item,R.id.listName, shoppingListNames);
        listItemsView.setAdapter(adapters);
        adapters.notifyDataSetChanged();
    }


    // creating the file for the list names
    public void saveItems() {

        String FILE_NAME = "list_names";
        // make reference to the list contents here:
        // String list = "List contents are here\n Hello";

        try {

            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

            try {
                for (String item : shoppingListNames) {
                    fos.write(item.getBytes());
                    fos.write("\n".getBytes());

                }
                fos.close();
            }
            catch (IOException e) {
                System.out.println("Could not write to file");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not open file");

        }
    }


    // loading saved items: list names
    public void loadItems() {
        try {
            FileInputStream fis = openFileInput("list_names");
            InputStreamReader inputStream = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(inputStream);

            String line;

            while ((line = buffer.readLine()) != null) {
                shoppingListNames.add(line);
            }
        }
        catch (Exception e) {
            System.out.println("Could not find file");
        }
    }


    // delete button for list names
    public void delete(View v) {

        ListView listNames = (ListView) findViewById(R.id.lists);
        int indexNo = listNames.getPositionForView(v);

        shoppingListNames.remove(indexNo);

        refreshList();
        saveItems();
    }

}  // end of class