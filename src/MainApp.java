import java.util.Scanner;

public class MainApp {
    private static Converter converter = new Converter();

    public static void main(String[] args) {
        System.out.println("Введите выражение в формате n+n без пробелов");
        Scanner console = new Scanner(System.in);
        String request = console.nextLine();
        System.out.println(calc(request));

    }

    public static String calc(String input) {
        String[] array = input.split("[+\\-*/]");
        boolean isRoman = false;
        String result = "";
        int firstNumber;
        int secondNumber;
        if (array.length != 2) {
            throw new RuntimeException("Введите 2 положительных, целых числа, и операнд для выполнения операции");
        }
        if (input.contains("[IVX]")) {
            isRoman = true;
        }
        if (isRoman) {
            firstNumber = Integer.parseInt(array[0]);
            secondNumber = Integer.parseInt(array[1]);
            result = String.valueOf(calculation(firstNumber, secondNumber, getOperation(input)));
        } else {
            firstNumber = converter.toArabian(array[0]);
            secondNumber = converter.toArabian(array[1]);
            int temp = calculation(firstNumber, secondNumber, getOperation(input));
            result = converter.toRoman(temp);
        }
        return result;
    }

    public static String getOperation(String input) {
        if (input.contains("+")) {
            return "+";
        } else if (input.contains("-")) {
            return "-";
        } else if (input.contains("*")) {
            return "*";
        } else if (input.contains("/")) {
            return "/";
        } else {
            return null;
        }
    }


    public static int calculation(int firstNumber, int secondNumber, String operation) {
        int result;
        if (firstNumber > 10 || firstNumber < 0 || secondNumber > 10 || secondNumber < 0) {
            throw new RuntimeException("Числа должны находиться в промежутке от 1 до 10 включительно");
        } else {
            switch (operation) {
                case "+" :
                    result = firstNumber + secondNumber;
                    return result;
                case "-":
                    result = firstNumber - secondNumber;
                    return result;
                case "*":
                    result = firstNumber * secondNumber;
                    return result;
                case "/" :
                    result = firstNumber / secondNumber;
                    return result;
                default:
                    throw new RuntimeException("Неверная операция");
            }
        }
    }
}
