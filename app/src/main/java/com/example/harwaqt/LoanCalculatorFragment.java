package com.example.harwaqt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class LoanCalculatorFragment extends Fragment {
    private EditText editTextLoanAmount;
    private EditText editTextNumberOfDays;
    private TextView textViewResult;

    public LoanCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_loan_calculator, container, false);

        editTextLoanAmount = view.findViewById(R.id.amount);
        editTextNumberOfDays = view.findViewById(R.id.days);
        textViewResult = view.findViewById(R.id.textview);

        Button btnCalculate = view.findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoan();
            }
        });

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void calculateLoan() {
        try {
            double loanAmount = Double.parseDouble(editTextLoanAmount.getText().toString());
            int numberOfDays = Integer.parseInt(editTextNumberOfDays.getText().toString());

            double dailyRate = 0.06 / 100; // 0.6%

            double markup = loanAmount * dailyRate * numberOfDays;

            double totalPayableAmount = loanAmount + markup;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, numberOfDays);
            Date dueDate = calendar.getTime();
            if (loanAmount < 500 || loanAmount > 25000 || numberOfDays < 1 || numberOfDays > 30) {
                textViewResult.setText("Loan amount must be between Rs. 500-25000 and days between 1-30 Days");
                return;
            }

            textViewResult.setText(String.format(Locale.getDefault(),
                    "Markup: %.2f\nDue Date: %s\nTotal Payable Amount: %.2f",
                    markup, sdf.format(dueDate), totalPayableAmount));

        } catch (NumberFormatException e) {
            textViewResult.setText("Please enter valid numbers");
        }
    }
}