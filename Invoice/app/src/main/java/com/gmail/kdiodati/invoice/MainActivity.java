package com.gmail.kdiodati.invoice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener{

    private EditText sub;
    private TextView percent;
    private TextView amount;
    private TextView tot;

    private String currSubTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get References
        sub = (EditText) findViewById(R.id.subTotalEdit);
        percent = (TextView) findViewById(R.id.percentEdit);
        amount = (TextView) findViewById(R.id.amountEdit);
        tot = (TextView) findViewById(R.id.totalEdit);

        sub.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }

    protected void calculateAndDisplay() {
        currSubTotal = sub.getText().toString();
        float currAmount;
        if (currSubTotal.equals("")) {
            currAmount = 0;
        }
        else {
            currAmount = Float.parseFloat(currSubTotal);
        }

        float discountPercent, discountAmount, totalAmount;
        if (currAmount >= 200.00) {
            discountPercent = .20f;
            discountAmount = currAmount * discountPercent;
            totalAmount = currAmount - discountAmount;
        }
        else if (currAmount >= 100.00) {
            discountPercent = .10f;
            discountAmount = currAmount * discountPercent;
            totalAmount = currAmount - discountAmount;
        }
        else {
            discountPercent = 0;
            discountAmount = currAmount * discountPercent;
            totalAmount = currAmount - discountAmount;
        }
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percentage = NumberFormat.getPercentInstance();
        percent.setText(percentage.format(discountPercent));
        amount.setText(currency.format(discountAmount));
        tot.setText(currency.format(totalAmount));
    }

}
