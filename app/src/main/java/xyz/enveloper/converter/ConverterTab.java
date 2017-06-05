package xyz.enveloper.converter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by dwiva on 5/18/17.
 */

public class ConverterTab extends Fragment{
    private View rootView;
    private String quantity, fromUnit, toUnit;
    private Spinner quantitySpinner;
    private Spinner fromSpinner;
    private Spinner toSpinner;

    private TextView tvInput;
    private TextView tvResult;

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnCe, btnDelete;
    private Button btnDot, btnPlusMinus;

    private Double input, result;

    private boolean loaded = false;
    MainActivity mainActivity;

    private Converter converter = new Converter();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        mainActivity = activity;

        rootView = inflater.inflate(R.layout.tab_converter, container, false);
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
        btnPlusMinus = (Button) rootView.findViewById(R.id.btn_plusminus);

        quantitySpinner = (Spinner) rootView.findViewById(R.id.quantity_spinner);
        fromSpinner = (Spinner) rootView.findViewById(R.id.from_unit_spinner);
        toSpinner = (Spinner) rootView.findViewById(R.id.to_unit_spinner);

        tvInput = (TextView) rootView.findViewById(R.id.from_unit);
        tvResult = (TextView) rootView.findViewById(R.id.to_unit);

        loaded = true;
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        tvInput.setText("0");

        ArrayAdapter<CharSequence> quantityAdapter;
        quantityAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.quantity_array, android.R.layout.simple_spinner_item);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);

        quantitySpinner.setOnItemSelectedListener(new CustomOnQuantitySelectedListener());
        fromSpinner.setOnItemSelectedListener(new CustomOnUnitSelectedListener());
        toSpinner.setOnItemSelectedListener(new CustomOnUnitSelectedListener());

        btn0.setOnClickListener(new CustomOnClickListener());
        btn1.setOnClickListener(new CustomOnClickListener());
        btn2.setOnClickListener(new CustomOnClickListener());
        btn3.setOnClickListener(new CustomOnClickListener());
        btn4.setOnClickListener(new CustomOnClickListener());
        btn5.setOnClickListener(new CustomOnClickListener());
        btn6.setOnClickListener(new CustomOnClickListener());
        btn7.setOnClickListener(new CustomOnClickListener());
        btn8.setOnClickListener(new CustomOnClickListener());
        btn9.setOnClickListener(new CustomOnClickListener());

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
                tvInput.setText("0");
                converting();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputBuf = tvInput.getText().toString();
                if (inputBuf.length() > 1) {
                    if (inputBuf.charAt(0) == '-' && inputBuf.length() == 2) {
                        tvInput.setText("0");
                    }
                    else {
                        inputBuf = inputBuf.substring(0, inputBuf.length() - 1);
                        tvInput.setText(inputBuf);
                    }
                }
                else {
                    tvInput.setText("0");
                }
                converting();
            }
        });

        btnPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputBuf = tvInput.getText().toString();
                if (!inputBuf.equals("0")) {
                    Double in = Double.parseDouble(inputBuf);
                    DecimalFormat df = new DecimalFormat("0");
                    df.setMaximumFractionDigits(12); //12 = DecimalFormat.DOUBLE_FRACTION_DIGITS
                    tvInput.setText("" + (df.format(in * -1)));

                    converting();
                }
            }
        });
    }

    public class CustomOnClickListener extends Activity implements AdapterView.OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) rootView.findViewById(v.getId());
            String value = btn.getText().toString();
            setInput(value);
            converting();
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

    public class CustomOnQuantitySelectedListener extends Activity implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String quantity = parent.getSelectedItem().toString();
            setUnitAdapter(quantity);
            refreshView();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public class CustomOnUnitSelectedListener extends Activity implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            converting();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void setUnitAdapter(String quantity){
        this.quantity = quantity;
        int array_id;
        switch (quantity) {
            case "Length":
                array_id = R.array.length_array;
                break;
            case "Area":
                array_id = R.array.area_array;
                break;
            case "Volume":
                array_id = R.array.volume_array;
                break;
            case "Mass":
                array_id = R.array.mass_array;
                break;
            case "Time":
                array_id = R.array.time_array;
                break;
            case "Temperature":
                array_id = R.array.temperature_array;
                break;
            case "Speed":
                array_id = R.array.speed_array;
                break;
            case "Energy":
                array_id = R.array.energy_array;
                break;
            case "Power":
                array_id = R.array.power_array;
                break;
            case "Pressure":
                array_id = R.array.pressure_array;
                break;
            case "Data":
                array_id = R.array.data_array;
                break;
            default:
                array_id = R.array.default_array;
                break;
        }
        ArrayAdapter<CharSequence> unitAdapter;
        unitAdapter = ArrayAdapter.createFromResource(this.getContext(), array_id, android.R.layout.simple_spinner_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(unitAdapter);
        toSpinner.setAdapter(unitAdapter);
    }

    private void refreshView() {
        if (quantity.equals("Temperature")) {
            btnPlusMinus.setVisibility(View.VISIBLE);
        }
        else {
            btnPlusMinus.setVisibility(View.INVISIBLE);
        }
    }

    private void converting() {
        quantity = quantitySpinner.getSelectedItem().toString();
        input = Double.parseDouble(tvInput.getText().toString());
        fromUnit = fromSpinner.getSelectedItem().toString();
        toUnit = toSpinner.getSelectedItem().toString();

        result = converter.convert(quantity, input, fromUnit, toUnit);
        showResult();
        mainActivity.saveResult(result);
    }

    private void showResult() {
        DecimalFormat df = new DecimalFormat("0");
        df.setMaximumFractionDigits(12); //12 = DecimalFormat.DOUBLE_FRACTION_DIGITS
        String r = "" + (df.format(result));

        if (r.length() < 20) {
            tvResult.setText(r);
        }
        else {
            tvResult.setText(result.toString());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && loaded) {
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

                converting();
            } else {
                tvInput.setText("0");
            }
        }
    }

//    private void setSharedResult() {
//        MainActivity.setResult(this.result);
//    }
//
//    private void getSharedResult() {
//
//        System.out.println(MainActivity.getResult());
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Toast.makeText(this.getContext(), "OnResume", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Toast.makeText(this.getContext(), "OnPause", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Toast.makeText(this.getContext(), "OnStop", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(this.getContext(), "OnDestroy", Toast.LENGTH_LONG).show();
//    }
}
