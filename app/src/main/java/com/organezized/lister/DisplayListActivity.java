package com.organezized.lister;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Damon on 9/05/15.
 * Class for displaying the 2/2 page for our app: the list name
 * at top and then the list contents below.
 * Note: List contents are stored as seperate listView items.
*/

public class DisplayListActivity extends ActionBarActivity {

    // Stores the list names
    ArrayList<String> shoppingListItems = new ArrayList<>();

    // Creates the view that is seen when passed to the new window
    // For editing list contents
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shopping_list_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String listName = intent.getStringExtra("LIST_NAME");

        TextView listNameField = (TextView) findViewById(R.id.listName);
        listNameField.setText(listName);

        loadItems();
        refreshList();
    }


    // Action bar stuff...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Don't know what this does?
    // A placeholder fragment containing a simple view.
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_shopping_list_edit,
                    container, false);
            return rootView;
        }
    }

    // ! change this for change list contents ***
    // This method adds items to the actual list.
    // This is the list contents.
    public void addListItemName(View view) {
        // create an EditText field using xml reference
        // and save to variable listItemName
        EditText listItemNameField = (EditText) findViewById(R.id.listItemName);
        String listItemName = listItemNameField.getText().toString();

        // Add the list items to array
        shoppingListItems.add(listItemName);

        System.out.println("List item method");
        for (String item: shoppingListItems) {

            System.out.println("Item: " + item);
        }

        refreshList();
        saveItems();

    }


    public void deleteListItem(View view) {

        ListView listItemsView = (ListView) findViewById(R.id.listItems);
        int indexNo = listItemsView.getPositionForView(view);

        shoppingListItems.remove(indexNo);

        refreshList();
        saveItems();
    }


    // Refresh list after changes are made
    public void refreshList() {
        // set the adapter using the instance above and refresh activity contents
        ListView listItemsView = (ListView) findViewById(R.id.listItems);
        ArrayAdapter adapters = new ArrayAdapter<String>(this, R.layout.shopping_list_item,R.id.listItemName, shoppingListItems);
        listItemsView.setAdapter(adapters);
        adapters.notifyDataSetChanged();
    }


    // creating the file for the list
    // list contents
    public void saveItems() {

        // get the name of list and put it in the file_name
        Intent intent = getIntent();
        String FILE_NAME = intent.getStringExtra("LIST_NAME");


        try {

            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

            try {
                for (String item : shoppingListItems) {
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


    // loading saved items
    public void loadItems() {
        Intent intent = getIntent();
        String FILE_NAME = intent.getStringExtra("LIST_NAME");

        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            InputStreamReader inputStream = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(inputStream);

            String line;

            while ((line = buffer.readLine()) != null) {
                shoppingListItems.add(line);
            }
        }
        catch (Exception e) {
            System.out.println("Could not find file");
        }
    }

}