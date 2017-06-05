package xyz.enveloper.converter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by dwiva on 5/18/17.
 */

public class FinanceTab extends Fragment{
    private View rootView;
    private String quantity, fromUnit, toUnit;
    private Spinner quantitySpinner;
    private Spinner fromSpinner;
    private Spinner toSpinner;

    private TextView etJumlahPinjaman;
    private TextView etBunga;
    private TextView etLamaPinjaman;

    private Button btnHitung;
    private Double input, result;
    MainActivity mainActivity;
    private Converter converter = new Converter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return rootView;
    }
}
