package summers.tipcalculator;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatProperty;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.View.OnClickListener;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener, OnClickListener {



    // defone memeber variables for the widgets
    private TextView percentTV;
    private TextView tipPercentTV;
    private TextView totalTV;
    private EditText billET;
    private Button percentUpButton;
    private Button percentDownButton;
    private Button resetButton;

    // define instance variables
    private String billAmountString = "";
    private float tipPercent = .15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the widget
        percentTV = (TextView) findViewById(R.id.percentTV);
        tipPercentTV = (TextView) findViewById(R.id.tipPercentTV);
        totalTV = (TextView) findViewById(R.id.totalTV);
        billET = (EditText) findViewById(R.id.billET);
        percentUpButton = (Button) findViewById(R.id.percentUpButton);
        percentDownButton = (Button) findViewById(R.id.percentDownButton);
        resetButton = (Button) findViewById(R.id.resetButton);


        //set listeners
        billET.setOnEditorActionListener(this);
        percentUpButton.setOnClickListener(this);
        percentDownButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.percentUpButton:
                tipPercent = tipPercent + .01f;
                calculateAndDisplay();
                break;
            case R.id.percentDownButton:
                tipPercent = tipPercent - .01f;
                calculateAndDisplay();
                break;
            case R.id.resetButton:
                tipPercent = .15f;
                calculateAndDisplay();
                break;

        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        calculateAndDisplay();
        return false;
    }

    private void calculateAndDisplay() {

        // get bill amount
        billAmountString = billET.getText().toString();
        float billAmount;

        if(billAmountString.equals("")){
            billAmount = 0;
        }
        else{
            billAmount = Float.parseFloat(billAmountString);
        }

        // calculate tip
        float percentAmount = tipPercent;
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        // display the formatted results
        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTV.setText(percent.format(percentAmount));

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipPercentTV.setText(currency.format(tipAmount));
        totalTV.setText(currency.format(totalAmount));
    }
}
