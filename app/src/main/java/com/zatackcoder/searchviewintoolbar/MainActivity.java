package com.zatackcoder.searchviewintoolbar;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    List<Item> items = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        items.add(new Item("Item 1", "Red", 10, 100.00));
        items.add(new Item("Item 2", "Red", 12, 100.00));
        items.add(new Item("Item 3", "Red", 14, 100.00));
        items.add(new Item("Item 4", "Red", 16, 150.00));
        items.add(new Item("Item 5", "Red", 18, 170.00));
        items.add(new Item("Item 6", "Green", 20, 190.00));
        items.add(new Item("Item 7", "Green", 10, 100.00));
        items.add(new Item("Item 8", "Green", 12, 200.00));
        items.add(new Item("Item 9", "Green", 14, 210.00));
        items.add(new Item("Item 10", "Green", 16, 240.00));
        items.add(new Item("Item 11", "Blue", 18, 250.00));
        items.add(new Item("Item 12", "Blue", 20, 280.00));
        items.add(new Item("Item 13", "Blue", 10, 300.00));
        items.add(new Item("Item 14", "Blue", 12, 150.00));
        items.add(new Item("Item 15", "White", 10, 170.00));

        mAdapter = new ItemAdapter(getApplicationContext(), items);
        mRecyclerView.setAdapter(mAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main_activity, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getApplicationContext().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                public boolean onQueryTextChange(String newText) {
                    List<Item> newList = new ArrayList<>();
                    for (Item item : items) {
                            if (item.getName().toLowerCase().contains(newText.toLowerCase())
                                    || item.getColor().toLowerCase().contains(newText.toLowerCase())
                                    || String.valueOf(item.getSize()).contains(newText)
                                    || String.valueOf(item.getPrice()).contains(newText)) {
                                newList.add(item);
                            }
                    }
                    mAdapter = new ItemAdapter(getApplicationContext(), newList);
                    mRecyclerView.setAdapter(mAdapter);
                    return true;
                }

                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }
}
