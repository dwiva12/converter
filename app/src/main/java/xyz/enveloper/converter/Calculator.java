package xyz.enveloper.converter;

/**
 * Created by dwiva on 5/30/17.
 */

public class Calculator {
    double calculate(String in) {
        String[][] expression = generateArray(in);
        System.out.println("length= " + expression.length);
        double result = Double.parseDouble(expression[0][0]);
        // expression.length - 1
        String operator;
        double value;

        for (int i = 0; i < expression.length - 1; i++) {
            operator = expression[i][1];
            value = Double.parseDouble(expression[i+1][0]);

            switch (operator) {
                case "*":
                    result = result * value;
                    break;
                case "/":
                    result = result / value;
                    break;
                case "+":
                    result = result + value;
                    break;
                case "-":
                    result = result - value;
                    break;
                default:
                    System.out.println("something went wrong");
                    break;
            }
            System.out.println(operator + " " + value);
        }

        return result;
    }

    String[][] generateArray(String in) {
        String[][] expression = new String[100][2];
        int j = 0;
        int start = 0;
        int stop;

        for (int i = 0; i < in.length(); i++) {
            char current = in.charAt(i);

            if (current == '*' || current == '/' || current == '+') {
                stop = i;
                expression[j][0] = in.substring(start, stop);
                System.out.println(i + ".." + start + ".." + stop);
                expression[j][1] = "" + in.charAt(i);
                start = i + 1;
                j++;
            }
            else if ( current == '-' && (i != 0) && isDigit(in.charAt(i-1)) ){
                stop = i;
                expression[j][0] = in.substring(start, stop);
                System.out.println(i + ".." + start + ".." + stop);
                expression[j][1] = "" + in.charAt(i);
                start = i + 1;
                j++;
            }
            else if (i == (in.length() - 1) ){
                System.out.println("work");
                stop = i + 1;
                expression[j][0] = in.substring(start, stop);
            }
        }

        System.out.println("generateArray finish");

        return trimArray(expression);
    }

    boolean isDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        else {
            return false;
        }
    }

    String[][] trimArray(String[][] array) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] == null) {
                index = i;
                break;
            }
        }
        System.out.println("index = " + index);

        String[][] result = new String[index][2];

        for (int i = 0; i < index; i++) {
            result[i][0] = array[i][0];
            result[i][1] = array[i][1];
        }

        return result;
    }
}
