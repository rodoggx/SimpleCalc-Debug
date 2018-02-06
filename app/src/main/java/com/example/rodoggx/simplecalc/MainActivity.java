package com.example.rodoggx.simplecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorActivity";
    private Calculator calculator;
    private EditText operandOneEditText;
    private EditText operandTwoEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        resultTextView = (TextView) findViewById(R.id.operation_result_text_view);
        operandOneEditText = (EditText) findViewById(R.id.operand_one_edit_text);
        operandTwoEditText = (EditText) findViewById(R.id.operand_two_edit_text);
    }

    public void onAdd(View view) {
        compute(Calculator.Operator.ADD);
    }

    public void onSub(View view) {
        compute(Calculator.Operator.SUB);
    }

    public void onDiv(View view) {
        try {
            compute(Calculator.Operator.DIV);
        } catch (IllegalArgumentException iae) {
            Log.e(TAG, "IllegalArgumentException", iae);
            resultTextView.setText(getString(R.string.computationError));
        }
    }

    public void onMul(View view) {
        compute(Calculator.Operator.MUL);
    }

    private void compute(Calculator.Operator operator) {
        double operandOne;
        double operandTwo;
        try {
            operandOne = getOperand(operandOneEditText);
            operandTwo = getOperand(operandTwoEditText);
        } catch (NumberFormatException nfe) {
            Log.e(TAG, "NumberFormatException", nfe);
            resultTextView.setText(getString(R.string.computationError));
            return;
        }

        String result;
        switch (operator) {
            case ADD:
                result = String.valueOf(calculator.add(operandOne, operandTwo));
                break;
            case SUB:
                result = String.valueOf(calculator.sub(operandOne, operandTwo));
                break;
            case DIV:
                result = String.valueOf(calculator.div(operandOne, operandTwo));
                break;
            case MUL:
                result = String.valueOf(calculator.mul(operandOne, operandTwo));
                break;
            default:
                result = getString(R.string.computationError);
                break;
        }
        resultTextView.setText(result);
    }

    /**
     * @return the operand value which was entered in an {@link EditText} as a double
     */
    private static Double getOperand(EditText operandEditText) {
        String operandText = getOperandText(operandEditText);
        return Double.valueOf(operandText);
    }

    /**
     * @return the operand text which was entered in an {@link EditText}.
     */
    private static String getOperandText(EditText operandEditText) {
        return operandEditText.getText().toString();
    }
}
