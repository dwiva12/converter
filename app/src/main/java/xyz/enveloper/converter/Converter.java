package xyz.enveloper.converter;

/**
 * Created by dwiva on 5/19/17.
 */

public class Converter {

    public double convert(String quantity, double in, String from, String to) {
        System.out.println( "qty: " + quantity + " in: " + in + " from: " + from + " to: " + to);
        //String[] index = new String[]{"length", "area", "volume", "mass", "time", "temperature", "speed", "energy", "power", "pressure", "data"};
        double out = 0;
        switch(quantity) {
            case "Length":
                out = length(in, from, to);
                break;
            case "Area":
                out = area(in, from, to);
                break;
            case "Volume":
                out = volume(in, from, to);
                break;
            case "Mass":
                out = mass(in, from, to);
                break;
            case "Time":
                out = time(in, from, to);
                break;
            case "Temperature":
                out = temperature(in, from, to);
                break;
            case "Speed":
                out = speed(in, from, to);
                break;
            case "Energy":
                out = energy(in, from, to);
                break;
            case "Power":
                out = power(in, from, to);
                break;
            case "Pressure":
                out = pressure(in, from, to);
                break;
            case "Data":
                out = data(in, from, to);
                break;
        }
        return out;
    }

    private double length(double in, String from, String to) {
        String[] index = new String[]{"nanometer", "micrometer", "milimeter", "centimeter", "meter", "kilometer", "inch", "mile"};
        double out;

        double[][] length = new double[][]{
                        //nano              micro       mili        centi       meter           kilo                inch                mile
            /*nano*/    {1,                 0.001,      0.000001,   0.0000001,  0.000000001,    0.000000000001,     0.0000000393701,    0.000000000000621371},
            /*micro*/   {1000,              1,          0.001,      0.0001,     0.000001,       0.000000001,        0.0000393701,       0.000000000621371},
            /*mili*/    {1000000,           1000,       1,          0.1,        0.001,          0.000001,           0.0393701,          0.000000621371},
            /*centi*/   {10000000,          10000,      10,         1,          0.01,           0.00001,            0.393701,           0.00000621371},
            /*meter*/   {1000000000,        1000000,    1000,       100,        1,              0.001,              39.3701,            0.000621371},
            /*kilo*/    {1000000000000d,    1000000000, 1000000,    100000,     1000,           1,                  39370.1,            0.621371},
            /*inch*/    {25400000,          25400,      25.4,       2.54,       0.0254,         0.0000254,          1,                  0.000015783},
            /*mile*/    {1609340000000d,    1609340000, 1609340,    160934,     1609.34,        1.60934,            63360,              1}
        };

        int i = generateIndex(index, from);
        int j = generateIndex(index, to);
        out = in * length[i][j];

        return out;
    }

    private double area(double in, String from, String to) {
        //TODO create the function body
        String[] index = new String[]{"centimeter", "meter", "kilometer", "inch", "mile", "hectare", "Acre"};
        double out;

        double[][] area = new double[][]{
                        //centi         meters          kilo            inch        mile        hectare         acre
            /*centi*/   {1,             0.0001,         0.0000000001},
            /*meter*/   {10000,         1,              0.000001},
            /*kilo*/    {10000000000d,  1000000,        1}
        };

        int i = generateIndex(index, from);
        int j = generateIndex(index, to);
        out = in * area[i][j];

        return out;
    }

    private double volume(double in, String from, String to) {
        //TODO create the function body
        return 0;
    }

    private double mass(double in, String from, String to) {
        String[] index = new String[]{"microgram", "miligram", "gram", "kilogram", "ton", "lb", "oz"};
        double out;

        double[][] mass = new double[][]{
                        //micro             mili        gram        kilogram        ton                 lb                  oz
            /*micro*/   {1,                 0.001,      0.000001,   0.000000001,    0.000000000001,     0.00000000220462,   0.000000035274},
            /*mili*/    {1000,              1,          0.001,      0.000001,       0.000000001,        0.00000220462,      0.000035274},
            /*gram*/    {1000000,           1000,       1,          0.001,          0.000001,           0.00220462,         0.035274},
            /*kilo*/    {1000000000,        1000000,    1000,       1,              0.001,              2.20462,            35.274},
            /*ton*/     {1000000000000d,    1000000000, 1000000,    1000,           1,                  2204.62,            35274},
            /*lb*/      {453592000,         453592,     453.592,    0.453592,       0.000453592,        1,                  16},
            /*oz*/      {28349500,          28349.5,    28.3495,    0.0283495,      0.0000283495,       0.0625,             1}
        };

        int i = generateIndex(index, from);
        int j = generateIndex(index, to);
        out = in * mass[i][j];

        return out;
    }

    private double time(double in, String from, String to) {
        //TODO create the function body
        String[] index = new String[]{"nanosecond", "microsecond", "milisecond", "second", "minute", "hour", "day", "week", "moon", "year", "decade", "century"};
        double out;

        double[][] time = new double[][]{
                        //nano              micro       mili        second          minute                hours        day          week        moon        Year        Decade      Century
            /*nano*/    {1,                 0.001,      0.000001,   0.000000001,    0.000000000001},
            /*micro*/   {1000,              1,          0.001,      0.000001,       0.000000001},
            /*mili*/    {1000000,           1000,       1,          0.001,          0.000001},
            /*second*/  {1000000000,        1000000,    1000,       1,              0.001},
            /*minute*/  {60000000000d,      60000000,   600000,     60,             1}
        };

        int i = generateIndex(index, from);
        int j = generateIndex(index, to);
        out = in * time[i][j];

        return out;
    }

    private double temperature(double in, String from, String to) {
        String[] index = new String[]{"reamur", "celsius", "fahrenheit", "kelvin"};
        double out;
        double[][] temp = new double[][]{
                        //reamur    celsius     fhrenht     kelvin
            /*scale*/   {4,         5,          9,          5},
            /*initial*/ {0,         0,          32,         273}
        };

        int i = generateIndex(index, from);
        int j = generateIndex(index, to);
        out = (in - temp[1][i]) * temp[0][j] / temp[0][i] + temp[1][j];

        return out;
    }

    private double speed(double in, String from, String to) {
        //TODO create the function body
        return 0;
    }

    private double energy(double in, String from, String to) {
        //TODO create the function body
        return 0;
    }

    private double power(double in, String from, String to) {
        //TODO create the function body
        return 0;
    }

    private double pressure(double in, String from, String to) {
        String[] index = new String[]{"pascal", "torr", "bar", "atmosphere"};
        double out;

        double[][] pressure = new double[][]{
                            //pascal    torr            bar             amosphere
            /*pascal*/      {1,         0.00750062,     0.00001,        0.0000098692},
            /*torr*/        {133.322,   1,              0.00133322,     0.00131579},
            /*bar*/         {100000,    750.062,        1,              0.986923},
            /*atmosphere*/  {101325,    760,            1.01325,        1}
        };

        int i = generateIndex(index, from);
        int j = generateIndex(index, to);
        out = in * pressure[i][j];

        return out;
    }

    private double data(double in, String from, String to) {
        String[] index = new String[]{"bit", "byte", "kilobit", "kilobyte", "megabit", "megabyte", "gigabit", "gigabyte", "terabit", "terabyte", "petabit", "petabyte"};
        double out;

        double[][] data = new double[][]{
                        //unit              kilo            mega        giga            tera            peta
            /*unit*/    {1,                 0.001,          0.000001,   0.000000001,    0.000000000001, 0.000000000000001},
            /*kilo*/    {1000,              1,              0.001,      0.000001,       0.000000001,    0.000000000001},
            /*mega*/    {1000000,           1000,           1,          0.001,          0.000001,       0.000000001},
            /*giga*/    {1000000000,        1000000,        1000,       1,              0.001,          0.000001},
            /*tera*/    {1000000000000d,    1000000000,     1000000,    1000,           1,              0.001},
            /*peta*/    {1000000000000000d, 1000000000000d, 1000000000, 1000000,        1000,           1}
        };

        int i = generateIndex(index, from);
        int j = generateIndex(index, to);
        int k = i % 2;
        int l = j % 2;
        i = i / 2;
        j = j / 2;

        if (k == l) {
            out = in * data[i][j];
        }
        else if (k > l) {
            out = in * data[i][j] * 8;
        }
        else {
            out = in * data[i][j] / 8;
        }

        return out;
    }

    private int generateIndex(String index[], String unit) {
        int num = 0;
        for (int i = 0; i < index.length; i++) {
            System.out.println(i + " " + index[i]);
            if (unit.equals(index[i])) {
                num = i;
                break;
            }
        }
        return num;
    }
}
