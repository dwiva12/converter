package xyz.enveloper.converter;

/**
 * Created by dwiva on 5/30/17.
 */

public class Calculator {
    double calculate(String input) {
        String[][] expression = generateArray(input);
        // System.out.println("length= " + expression.length);
        double result = Double.parseDouble(expression[0][0]);
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
            // System.out.println(operator + " " + value);
        }

        return result;
    }

    private String[][] generateArray(String input) {
        //Fungsi ini telah dipindahkan ke view
        /*for (int i = input.length()-1; i >= 0; i--) { //hapus operator di belakang angka terakhir jika ada.
            if (isDigit(input.charAt(i))) {
                input = input.substring(0, i+1);
                break;
            }
        }*/

        int index = 1;
        for (int i = 0; i < input.length(); i++) { //menentukan ukuran array expression
            char current = input.charAt(i);
            if (current == '*' || current == '/' || current == '+' || (current == '-' && (i != 0) && isDigit(input.charAt(i-1))) ) {
                index++;
            }
        }

        String[][] expression = new String[index][2];
        int j = 0;
        int start = 0;
        int stop;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current == '*' || current == '/' || current == '+' || (current == '-' && (i != 0) && isDigit(input.charAt(i-1))) ) {
                stop = i;
                expression[j][0] = input.substring(start, stop);
                expression[j][1] = "" + input.charAt(i);
                start = i + 1;
                j++;
            }
            else if (i == (input.length() - 1) ){
                stop = i + 1;
                expression[j][0] = input.substring(start, stop);
            }
        }

        return expression;
    }

    boolean isDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        else {
            return false;
        }
    }
}
