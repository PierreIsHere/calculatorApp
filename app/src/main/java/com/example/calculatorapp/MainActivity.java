package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private float prevNumber = 0;
    private String operation = "";
    private boolean hasDecimal = false;
//    private TextView calcScreen = (TextView) findViewById(R.id.calcScreen);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void appendDigit(View view){
        TextView calcScreen = (TextView) findViewById(R.id.calcScreen);
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        String currentNum = calcScreen.getText().toString();
        calcScreen.setText(currentNum + buttonText);
    }

    public void appendDecimal(View view) {
        TextView calcScreen = (TextView) findViewById(R.id.calcScreen);
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        String currentNum = calcScreen.getText().toString();
        if(currentNum == ""){
            return;
        }
        if (hasDecimal == true) {
            return;
        } else {
            hasDecimal = true;
            calcScreen.setText(currentNum + buttonText);
        }
    }

    public void storeNumber(){
        TextView calcScreen = (TextView) findViewById(R.id.calcScreen);
        String currentNum = calcScreen.getText().toString();
        if (currentNum == ""){
            return;
//            prevNumber = 0;
        }else {
            prevNumber = Float.parseFloat(currentNum);
        }
        calcScreen.setText("");
    }

    public void operate(View view){
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        operation = buttonText;
        storeNumber();
        hasDecimal = false;
    }

    public void equate(View view) {
        TextView calcScreen = (TextView) findViewById(R.id.calcScreen);
        String currentNumStr = calcScreen.getText().toString();
        float currentNum;
        if (currentNumStr ==""){
            currentNum = 0;
        }else{
            currentNum = Float.parseFloat(currentNumStr);
        }
        switch (operation) {
            case "+":
                calcScreen.setText(Float.toString(prevNumber + currentNum));
                break;
            case "-":
                calcScreen.setText(Float.toString(prevNumber - currentNum));
                break;
            case "x":
                calcScreen.setText(Float.toString(prevNumber * currentNum));
                break;
            case "/":
                if(currentNum == 0){
                    Snackbar snackbar = Snackbar.make(view,"Cannot Divide by 0", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else {
                    calcScreen.setText(Float.toString(prevNumber / currentNum));
                }
                break;
            default:;
        }
        operation = "";
        prevNumber = currentNum;
    }

    public void clearScreen(View view){
        TextView calcScreen = (TextView) findViewById(R.id.calcScreen);
        calcScreen.setText("");
        hasDecimal = false;
    }
}
