package com.cookingwithcode.lomboktraditionalfoods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityDetailFood extends AppCompatActivity{
    public static final String EXT_FOOD_NUM = "number";
    public static final String EXT_FOOD_NAME = "name";
    private final String STATE_LIST = "state_list";
    private ArrayList<Food> list = new ArrayList<>();
    private RecyclerView rvCategory;
    private String data[][] = FoodData.getData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        int foodNum = getIntent().getIntExtra(EXT_FOOD_NUM,0);
        String foodName = getIntent().getStringExtra(EXT_FOOD_NAME);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        getSupportActionBar().setTitle(foodName);
        list = new ArrayList<>();

        if (savedInstanceState == null){
            inputToList(foodNum);
            showDetail();
        }else {
            ArrayList<Food> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            list.addAll(stateList);
            showDetail();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, list);
    }

    private void showDetail(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        AdapterDetailFood adapterDetailFood = new AdapterDetailFood(this);
        adapterDetailFood.setListFoodDetail(list);
        rvCategory.setAdapter(adapterDetailFood);
    }

    private void inputToList(int position){
        Food f = new Food();
        f.setName(data[position][0]);
        f.setRemarks(data[position][1]);
        f.setPhoto(data[position][2]);
        f.setContent(data[position][3]);
        list.add(f);
    }
}
