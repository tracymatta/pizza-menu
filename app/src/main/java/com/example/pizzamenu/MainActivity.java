package com.example.pizzamenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView price;
    RadioGroup size;
    CheckBox sausage;
    CheckBox olives;
    CheckBox pepperoni;
    CheckBox mushrooms;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price = (TextView) findViewById(R.id.price);
        size = (RadioGroup) findViewById(R.id.size);
        sausage = (CheckBox) findViewById(R.id.sausage);
        olives = (CheckBox) findViewById(R.id.olives);
        pepperoni = (CheckBox) findViewById(R.id.pepperoni);
        mushrooms = (CheckBox) findViewById(R.id.mushrooms);
        list = new ArrayList<String>();
    }

    public void calculate(View v) {
        int selected = size.getCheckedRadioButtonId();
        if(selected == -1) {
            return;
        }

        RadioButton selectedSize = (RadioButton) findViewById(selected);
        String choice = selectedSize.getText().toString();

        int sum = 0;
        switch (choice) {
            case "Small":
                sum = 80000;
                break;
            case "Medium":
                sum = 100000;
                break;
            case "Large":
                sum = 120000;
                break;
        }

        if(sausage.isChecked()) {
            sum += 20000;
        }
        if(olives.isChecked()) {
            sum += 20000;
        }
        if(pepperoni.isChecked()) {
            sum += 20000;
        }
        if(mushrooms.isChecked()) {
            sum += 20000;
        }

        price.setText(sum + " LBP");
    }

    public void addToCart(View v) {
        price.setText("Price item = 0 LBP");
        String pizza = "";
        int selected = size.getCheckedRadioButtonId();
        if(selected == -1) {
            return;
        }

        RadioButton selectedSize = (RadioButton) findViewById(selected);
        String choice = selectedSize.getText().toString();

        pizza += choice;
        int sum = 0;
        switch (choice) {
            case "Small":
                sum = 80000;
                break;
            case "Medium":
                sum = 100000;
                break;
            case "Large":
                sum = 120000;
                break;
        }

        if(sausage.isChecked()) {
            pizza += " - Sausage";
            sum += 20000;
            sausage.toggle();
        }
        if(olives.isChecked()) {
            pizza += " - Olives";
            sum += 20000;
            olives.toggle();
        }
        if(pepperoni.isChecked()) {
            pizza += " - Pepperoni";
            sum += 20000;
            pepperoni.toggle();
        }
        if(mushrooms.isChecked()) {
            pizza += " - Mushrooms";
            sum += 20000;
            mushrooms.toggle();
        }

        pizza += "  - Price = " + sum + " LBP";
        list.add(pizza);
        size.clearCheck();
    }

    public void payBill(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("list", list);
        startActivity(intent);
    }
}