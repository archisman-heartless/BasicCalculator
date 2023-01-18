package com.example.basiccalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result, solution;
    MaterialButton button_c, button_fbracket, button_sbracket;
    MaterialButton button_divide, button_plus, button_minus, button_x, button_equal, button_dot, button_percent;
    MaterialButton button_9, button_8, button_7, button_6, button_5, button_4, button_3, button_2, button_1, button_0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(button_c, R.id.button_c);
        assignId(button_9, R.id.button_9);
        assignId(button_8, R.id.button_8);
        assignId(button_7, R.id.button_7);
        assignId(button_6, R.id.button_6);
        assignId(button_5, R.id.button_5);
        assignId(button_4, R.id.button_4);
        assignId(button_3, R.id.button_3);
        assignId(button_2, R.id.button_2);
        assignId(button_1, R.id.button_1);
        assignId(button_fbracket, R.id.button_fbracket);
        assignId(button_sbracket, R.id.button_divide);
        assignId(button_percent, R.id.button_percent);
        assignId(button_dot, R.id.button_dot);
        assignId(button_equal, R.id.button_equal);
        assignId(button_x, R.id.button_x);
        assignId(button_minus, R.id.button_minus);
        assignId(button_plus, R.id.button_plus);
        assignId(button_divide, R.id.button_divide);
        assignId(button_0, R.id.button_0);

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if (buttonText.equals("AC")) {
            solution.setText("");
            result.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solution.setText(result.getText());
            return;
        }
        if (buttonText.equals("c")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);




        if(!finalResult.equals("Err"))

    {
        result.setText(finalResult);
    }

}
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }

        }
    }


