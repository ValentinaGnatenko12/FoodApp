package com.cookingwithcode.lomboktraditionalfoods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String STATE_LIST="state_list";
    private final String STATE_MODE="state_mode";

    private RecyclerView rvCategory;
    private ArrayList<Food> list = new ArrayList<>();

    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        getSupportActionBar().setTitle("Lombok Traditional Foods");

        if (savedInstanceState == null){
            list.addAll(FoodData.getListData());
            showRecyclerList();
            mode = R.id.action_list;
        }else {
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            ArrayList<Food> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            list.addAll(stateList);
            setMode(stateMode);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }

    private void showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        AdapterListFood adapterListFood = new AdapterListFood(this);
        adapterListFood.setListFood(list);
        rvCategory.setAdapter(adapterListFood);
        clickItem();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        AdapterCardViewFood adapterCardViewFood = new AdapterCardViewFood(this);
        adapterCardViewFood.setListFood(list);
        rvCategory.setAdapter(adapterCardViewFood);

        clickItem();
    }

    private void showSelectedFood(Food food){
        Toast.makeText(this, food.getName(), Toast.LENGTH_SHORT).show();
    }

    private void clickItem(){
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                Intent mvNameFood = new Intent(MainActivity.this, ActivityDetailFood.class);
                mvNameFood.putExtra(ActivityDetailFood.EXT_FOOD_NUM, position);
                mvNameFood.putExtra(ActivityDetailFood.EXT_FOOD_NAME, list.get(position).getName());
                showSelectedFood(list.get(position));
                startActivity(mvNameFood);
            }
        });
    }

    private void setMode(int selectedMode){
        switch (selectedMode){
            case R.id.action_list:
                showRecyclerList();
                break;
            case R.id.action_cardview:
                showRecyclerCardView();
                break;
        }
        mode = selectedMode;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
}
