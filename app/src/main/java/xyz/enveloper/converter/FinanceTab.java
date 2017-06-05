package xyz.enveloper.converter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by dwiva on 5/18/17.
 */

public class FinanceTab extends Fragment{
    private View rootView;

    private EditText etJumlahPinjaman;
    private EditText etBunga;
    private EditText etLamaPinjaman;
    private TextView tvHasil;

    private Button btnHitung;

    private double jumlahPinjaman;
    private int lamaPinjaman;
    private double bunga;
    private double hasil;

    private Finance finance = new Finance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_finance, container, false);

        btnHitung = (Button) rootView.findViewById(R.id.hitung);
        etJumlahPinjaman = (EditText) rootView.findViewById(R.id.jumlah_id);
        etLamaPinjaman = (EditText) rootView.findViewById(R.id.bulan_id);
        etBunga = (EditText) rootView.findViewById(R.id.bunga_id);
        tvHasil = (TextView) rootView.findViewById(R.id.hasil_id);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jumlahPinjamanBuff = etJumlahPinjaman.getText().toString();
                String lamaPinjamanBuff = etLamaPinjaman.getText().toString();
                String bungaBuff = etBunga.getText().toString();

                if (isNumber(jumlahPinjamanBuff) &&  isNumber(lamaPinjamanBuff) && isNumber(bungaBuff)) {
                    jumlahPinjaman = Double.parseDouble(jumlahPinjamanBuff);
                    lamaPinjaman = Integer.parseInt(lamaPinjamanBuff);
                    bunga = Double.parseDouble(bungaBuff);

                    hasil = finance.hutang(jumlahPinjaman, lamaPinjaman, bunga);

                    System.out.println(jumlahPinjaman + " " + lamaPinjaman + " ");

                    DecimalFormat df = new DecimalFormat("0");
                    df.setMaximumFractionDigits(12); //12 = DecimalFormat.DOUBLE_FRACTION_DIGITS
                    String r = "" + (df.format(hasil));

                    tvHasil.setText(r);
                }

            }
        });
    }

    private boolean isNumber(String input) {
        boolean b = false;
        if (!input.equals("")) {

            for (int i = 0; i < input.length(); i++) {
                char current = input.charAt(i);
                if( !((current >= '0' && current <= '9') || current == '.') ) {
                    b = false;
                    break;
                }
                else {
                    b =  true;
                }
            }
        }
        else {
            b =  false;
        }

        return b;
    }
}
