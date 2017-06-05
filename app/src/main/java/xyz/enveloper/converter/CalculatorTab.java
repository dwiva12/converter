package xyz.enveloper.converter;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by dwiva on 5/18/17.
 */

public class CalculatorTab extends Fragment{
    private View rootView;

    private TextView tvInput;
    private TextView tvResult;

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnCe, btnDelete, btnDot;
    private Button btnDivide, btnMultiply, btnPlus, btnMinus, btnEqual;

    String input;
    private Double result;
    private boolean fromResult = false;
    MainActivity mainActivity;

    Calculator calculator = new Calculator();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        mainActivity = activity;

        this.rootView = inflater.inflate(R.layout.tab_calculator, container, false);
        btn0 = (Button) rootView.findViewById(R.id.btn_0);
        btn1 = (Button) rootView.findViewById(R.id.btn_1);
        btn2 = (Button) rootView.findViewById(R.id.btn_2);
        btn3 = (Button) rootView.findViewById(R.id.btn_3);
        btn4 = (Button) rootView.findViewById(R.id.btn_4);
        btn5 = (Button) rootView.findViewById(R.id.btn_5);
        btn6 = (Button) rootView.findViewById(R.id.btn_6);
        btn7 = (Button) rootView.findViewById(R.id.btn_7);
        btn8 = (Button) rootView.findViewById(R.id.btn_8);
        btn9 = (Button) rootView.findViewById(R.id.btn_9);
        btnCe = (Button) rootView.findViewById(R.id.btn_ce);
        btnDelete = (Button) rootView.findViewById(R.id.btn_del);
        btnDot = (Button) rootView.findViewById(R.id.btn_dot);
        btnDivide = (Button) rootView.findViewById(R.id.btn_divide);
        btnMultiply = (Button) rootView.findViewById(R.id.btn_multiply);
        btnPlus = (Button) rootView.findViewById(R.id.btn_plus);
        btnMinus = (Button) rootView.findViewById(R.id.btn_minus);
        btnEqual = (Button) rootView.findViewById(R.id.btn_equal);

        tvInput = (TextView) rootView.findViewById(R.id.input);
        tvResult = (TextView) rootView.findViewById(R.id.output);

        return rootView;
    }

    public void onStart() {
        super.onStart();

        btn0.setOnClickListener(new NumberOnClickListener());
        btn1.setOnClickListener(new NumberOnClickListener());
        btn2.setOnClickListener(new NumberOnClickListener());
        btn3.setOnClickListener(new NumberOnClickListener());
        btn4.setOnClickListener(new NumberOnClickListener());
        btn5.setOnClickListener(new NumberOnClickListener());
        btn6.setOnClickListener(new NumberOnClickListener());
        btn7.setOnClickListener(new NumberOnClickListener());
        btn8.setOnClickListener(new NumberOnClickListener());
        btn9.setOnClickListener(new NumberOnClickListener());

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputBuf = tvInput.getText().toString();
                String num = "";

                for (int i = inputBuf.length()-1; i >= 0; i--) {
                    char current = inputBuf.charAt(i);

                    if ( !(current >= '0' && current <= '9') ) {
                        int start = i-1;
                        num = inputBuf.substring(start, inputBuf.length());
                        System.out.println(i + ".." + start + ".." + num);
                        break;
                    }
                }

                if (!num.contains(".")) {
                    inputBuf = inputBuf + ".";
                    tvInput.setText(inputBuf);
                }
            }
        });

        btnCe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText("");
                tvResult.setText("");
                result = 0d;
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputBuf = tvInput.getText().toString();
                if (inputBuf.length() > 0) {
                    if (!fromResult) {
                        inputBuf = inputBuf.substring(0, inputBuf.length() - 1);
                        tvInput.setText(inputBuf);
                        if (!inputBuf.equals("")){
                            calculate();
                        }
                        else {
                            tvInput.setText("");
                            tvResult.setText("");
                            result = 0d;
                        }
                    }
                    else {
                        tvInput.setText("");
                        result = 0d;
                    }

                }
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) rootView.findViewById(v.getId());
                String operator = btn.getText().toString();
                String inputBuf = tvInput.getText().toString();

                if (!inputBuf.equals("")) {
                    char last = inputBuf.charAt(inputBuf.length()-1);
                    if (last >= '0' && last <= '9') {
                        inputBuf = inputBuf + operator;
                        fromResult = false;
                    }
                    else if (inputBuf.length() != 1) {
                        char last2 = inputBuf.charAt(inputBuf.length()-2);
                        if (!(last2 >= '0' && last2 <= '9')) {
                            inputBuf = inputBuf.substring(0, inputBuf.length() - 2) + operator;
                        } else {
                            inputBuf = inputBuf.substring(0, inputBuf.length() - 1) + operator;
                        }
                    }
                    tvInput.setText(inputBuf);
                }
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) rootView.findViewById(v.getId());
                String operator = btn.getText().toString();
                String inputBuf = tvInput.getText().toString();

                if (!inputBuf.equals("")) {
                    char last = inputBuf.charAt(inputBuf.length()-1);
                    if (last >= '0' && last <= '9') {
                        inputBuf = inputBuf + operator;
                        fromResult = false;
                    }
                    else if (inputBuf.length() != 1){
                        char last2 = inputBuf.charAt(inputBuf.length()-2);
                        if (!(last2 >= '0' && last2 <= '9')) {
                            inputBuf = inputBuf.substring(0, inputBuf.length() - 2) + operator;
                        } else {
                            inputBuf = inputBuf.substring(0, inputBuf.length() - 1) + operator;
                        }
                    }
                    tvInput.setText(inputBuf);
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) rootView.findViewById(v.getId());
                String operator = btn.getText().toString();
                String inputBuf = tvInput.getText().toString();

                if (!inputBuf.equals("")) {
                    char last = inputBuf.charAt(inputBuf.length()-1);
                    if (last >= '0' && last <= '9') {
                        inputBuf = inputBuf + operator;
                        fromResult = false;
                    }
                    else if (inputBuf.length() != 1) {
                        char last2 = inputBuf.charAt(inputBuf.length()-2);
                        if (!(last2 >= '0' && last2 <= '9')) {
                            inputBuf = inputBuf.substring(0, inputBuf.length() - 2) + operator;
                        } else {
                            inputBuf = inputBuf.substring(0, inputBuf.length() - 1) + operator;
                        }
                    }
                    tvInput.setText(inputBuf);
                }
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) rootView.findViewById(v.getId());
                String operator = btn.getText().toString();
                String inputBuf = tvInput.getText().toString();
                btn = (Button) rootView.findViewById(R.id.btn_plus);
                String  plus = btn.getText().toString();
                btn = (Button) rootView.findViewById(R.id.btn_minus);
                String  minus = btn.getText().toString();

                if (inputBuf.equals("")) {
                    inputBuf = inputBuf + operator;
                    fromResult = false;
                }
                else {
                    String c = "" + inputBuf.charAt(inputBuf.length()-1);
                    if ( !(c.equals(minus)) && !(c.equals(plus)) ) {
                        inputBuf = inputBuf + operator;
                        fromResult = false;
                    }
                    else {
                        inputBuf = inputBuf.substring(0, inputBuf.length() - 1) + operator;
                    }
                }

                tvInput.setText(inputBuf);
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fromResult && !(tvInput.getText().toString().equals("")) ) {
                    DecimalFormat df = new DecimalFormat("0");
                    df.setMaximumFractionDigits(10); //12 = DecimalFormat.DOUBLE_FRACTION_DIGITS
                    String r = "" + (df.format(result));

                    if (r.length() < 12) {
                        tvInput.setText(r);
                    }
                    else {
                        tvInput.setText(result.toString());
                    }

                    tvResult.setText("");
                    fromResult = true;
                }
            }
        });
//        Toast.makeText(this.getContext(), "OnStart Finish", Toast.LENGTH_LONG).show();
    }

    public class NumberOnClickListener extends Activity implements AdapterView.OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) rootView.findViewById(v.getId());
            String value = btn.getText().toString();
            setInput(value);
            calculate();
        }

    }

    private void setInput(String value) {
        String inputBuf = tvInput.getText().toString();
        if (inputBuf.equals("0") || fromResult) {
            if (value.equals("0")) {
                tvInput.setText("0");
                fromResult = false;
            }
            else {
                tvInput.setText(value);
                fromResult = false;
            }
        }
        else {
            inputBuf = inputBuf + value;
            tvInput.setText(inputBuf);
        }

    }

    private void calculate() {
        String input = tvInput.getText().toString();
        if (!input.equals("")) {
            for (int i = input.length()-1; i >= 0; i--) { //hapus operator di belakang angka terakhir jika ada.
                if (calculator.isDigit(input.charAt(i))) {
                    input = input.substring(0, i+1);
                    break;
                }
            }
            if (!input.equals("−")) {
//                System.out.println(input);
                input = input.replace('×', '*');
                input = input.replace('÷', '/');
                input = input.replace('+', '+');
                input = input.replace('−', '-');
//                System.out.println(input);

                result = calculator.calculate(input);
                mainActivity.saveResult(result);
                showResult();
            }
            else {
                tvResult.setText("");
            }
        }
        else {
            tvResult.setText("");
        }
    }

    private void showResult() {
        DecimalFormat df = new DecimalFormat("0");
        df.setMaximumFractionDigits(10); //12 = DecimalFormat.DOUBLE_FRACTION_DIGITS
        String r = "" + (df.format(result));

        if (r.length() < 12) {
            tvResult.setText(r);
        }
        else {
            tvResult.setText(result.toString());
        }
    }

    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            result = mainActivity.getSavedResult();
            if(result != 0.0) {
                DecimalFormat df = new DecimalFormat("0");
                df.setMaximumFractionDigits(10); //12 = DecimalFormat.DOUBLE_FRACTION_DIGITS
                String r = "" + (df.format(result));

                if (r.length() < 12) {
                    tvInput.setText(r);
                }
                else {
                    tvInput.setText(result.toString());
                }
            } else {
                tvInput.setText("0");
            }
        }
    }*/
}
