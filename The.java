import java.util.Scanner;
public class The {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (например, 3 + 5):");
        String input = scanner.nextLine();

        try {
            System.out.println(calc(input));
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) {
        input = input.replaceAll("\\s+", "");
        if (!input.matches("^(\\d+|[IVXLCDM]+)([+\\-*/])(\\d+|[IVXLCDM]+)$")) {
            throw new IllegalArgumentException("Неверный формат ввода!");
        }
        String[] parts = input.split("(?=[+\\-*/])|(?<=[+\\-*/])");
        String firstOperand = parts[0];
        String operator = parts[1];
        String secondOperand = parts[2];
        boolean isRoman = isRoman(firstOperand) && isRoman(secondOperand);
        int num1 = isRoman ? romanToArabic(firstOperand) : Integer.parseInt(firstOperand);
        int num2 = isRoman ? romanToArabic(secondOperand) : Integer.parseInt(secondOperand);

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10!");
        }
        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Деление на ноль!");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неверная операция!");
        }
        if (isRoman) {
            if (result <= 0) {
                throw new IllegalArgumentException("Римские числа должны быть положительными!");
            }
            return arabicToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }
    private static boolean isRoman(String str) {
        return str.matches("[IVXLCDM]+");
    }
    private static int romanToArabic(String roman) {
        int result = 0;
        int prevValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            char c = roman.charAt(i);
            int value = romanCharToValue(c);

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }

            prevValue = value;
        }
        return result;
    }
    private static int romanCharToValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: throw new IllegalArgumentException("Некорректный символ римского числа!");
        }
    }
    private static String arabicToRoman(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] + hundreds[(num % 1000) / 100] + tens[(num % 100) / 10] + ones[num % 10];
    }
}