package xyz.enveloper.converter;

/**
 * Created by dwiva on 6/5/17.
 */

public class Finance {
    public double[] hutang(double jumlahPinjaman, int lamaPinjaman, double bunga) {
        double[] result = new double[2];
        double hasil;
        double total;

        hasil = (jumlahPinjaman / lamaPinjaman) + (jumlahPinjaman * bunga);
        total = hasil * lamaPinjaman;

        result[0] = hasil;
        result[1] = total;

        return result;
    }

    public double[] hutangMenurun(double jumlahPinjaman, int lamaPinjaman, double bunga) {
        double[] hasil =  new double[lamaPinjaman + 1];
        double pokok = jumlahPinjaman / lamaPinjaman;
        double sisaHutang = jumlahPinjaman;
        double total = 0;

        for (int i = 0; i < lamaPinjaman; i++) {
            hasil[i] = pokok + (sisaHutang * bunga);
            sisaHutang = sisaHutang - hasil[i];
            total = total + hasil[i];
        }

        hasil[lamaPinjaman] = total;

        return hasil;
    }
}
