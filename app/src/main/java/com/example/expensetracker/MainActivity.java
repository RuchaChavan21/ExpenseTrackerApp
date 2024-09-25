package com.example.expensetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText descriptionInput, amountInput;
    private Button addExpenseBtn;
    private TextView totalExpenses;
    private ListView expenseListView;

    private ArrayList<String> expenseList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private double totalAmount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptionInput = findViewById(R.id.descriptionInput);
        amountInput = findViewById(R.id.amountInput);
        addExpenseBtn = findViewById(R.id.addExpenseBtn);
        totalExpenses = findViewById(R.id.totalExpenses);
        expenseListView = findViewById(R.id.expenseListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseList);
        expenseListView.setAdapter(adapter);

        addExpenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });
    }

    private void addExpense() {
        String description = descriptionInput.getText().toString().trim();
        String amountText = amountInput.getText().toString().trim();

        if (description.isEmpty() || amountText.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountText);
        totalAmount += amount;
        totalExpenses.setText("Total Expenses: ₹" + totalAmount);

        String expense = description + ": ₹" + amount;
        expenseList.add(expense);
        adapter.notifyDataSetChanged();

        descriptionInput.setText("");
        amountInput.setText("");
    }
}
