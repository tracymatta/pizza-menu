package com.example.pizzamenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView cart;
    ArrayList<String> list;
    TextView total;
    int totalPrice;

    public static int price(String pizza) {
        String[] pizzalist = pizza.split(" - ");
        String[] splitpizza = pizzalist[pizzalist.length - 1].split(" ");
        return Integer.parseInt(splitpizza[2]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        cart = (ListView) findViewById(R.id.cartList);
        total = (TextView) findViewById(R.id.total);
        totalPrice = 0;

        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("list");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        cart.setAdapter(adapter);

        cart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                totalPrice -= price(list.get(position));
                list.remove(position);
                adapter.notifyDataSetChanged();
                total.setText("Total Price = " + totalPrice + " LBP");
                return true;
            }
        });

        for(int i = 0; i < list.size(); i += 1) {
            totalPrice += price(list.get(i));
        }

        total.setText("Total Price = " + totalPrice + " LBP");

    }

}