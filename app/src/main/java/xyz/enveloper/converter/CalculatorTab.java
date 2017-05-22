package xyz.enveloper.converter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dwiva on 5/18/17.
 */

public class CalculatorTab extends Fragment{
    private View rootView;

    private TextView tvInput;
    private TextView tvResult;

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnCe, btnDelete, btnDot;
    private Button btnDivide, btnMultiply, btnPlus, btnMinus;

    private Double input, result;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
                if (!inputBuf.contains(".")) {
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
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputBuf = tvInput.getText().toString();
                if (inputBuf.length() > 0) {
                    inputBuf = inputBuf.substring(0, inputBuf.length() - 1);
                    tvInput.setText(inputBuf);
                }
            }
        });

        /*btnPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputBuf = tvInput.getText().toString();
                if (!inputBuf.equals("0")) {
                    Double in = Double.parseDouble(inputBuf);
                    DecimalFormat df = new DecimalFormat("0");
                    df.setMaximumFractionDigits(12); //12 = DecimalFormat.DOUBLE_FRACTION_DIGITS
                    tvInput.setText("" + (df.format(in * -1)));

                }
            }
        });*/
        btnDivide.setOnClickListener(new OperatorOnClickListener());
        btnMultiply.setOnClickListener(new OperatorOnClickListener());
        btnPlus.setOnClickListener(new OperatorOnClickListener());
        btnMinus.setOnClickListener(new OperatorOnClickListener());
//        Toast.makeText(this.getContext(), "OnStart Finish", Toast.LENGTH_LONG).show();
    }

    public class OperatorOnClickListener extends Activity implements AdapterView.OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) rootView.findViewById(v.getId());
            String value = btn.getText().toString();
            setInput(value);
        }
    }

    public class NumberOnClickListener extends Activity implements AdapterView.OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) rootView.findViewById(v.getId());
            String value = btn.getText().toString();
            setInput(value);
        }

    }

    private void setInput(String value) {
        String inputBuf = tvInput.getText().toString();
        if (inputBuf.equals("0")) {
            if (value.equals("0")) {
                tvInput.setText("0");
            }
            else {
                tvInput.setText(value);
            }
        }
        else {
            inputBuf = inputBuf + value;
            tvInput.setText(inputBuf);
        }

    }
}
