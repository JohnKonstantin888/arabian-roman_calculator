import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Введите выражение: ");
        Scanner console = new Scanner(System.in);
        String request = console.nextLine();
        System.out.println(calc(request));
    }

    public static String calc(String input) {
        int result;
        Converter converter = new Converter();
        String[] operations = {"+", "-", "/", "*"};
        String[] regexOperations = {"\\+", "-", "/", "\\*"};

        //Определяем арифметическое действие:
        int operationIndex=-1;
        for (int i = 0; i < operations.length; i++) {
            if(input.contains(operations[i])){
                operationIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(operationIndex==-1){
            throw new RuntimeException("Некорректное выражение");
        }
        //Делим строчку по найденному арифметическому знаку
        String[] data = input.split(regexOperations[operationIndex]);
        if (data.length != 2) {
            throw new RuntimeException("Должо быть два операнда");
        }
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman) {
                //если римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
                maxMinExceptions(a, b);
            } else {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
                maxMinExceptions(a, b);
            }
            //выполняем с числами арифметическое действие

            switch (operations[operationIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
                default:
                    throw new RuntimeException("Некорректное выражение");
            }
            //15->XV
            if(isRoman) {
                //если числа были римские, возвращаем результат в римском числе
                return String.valueOf(converter.intToRoman(result));
            } else {
                //если числа были арабские, возвращаем результат в арабском числе
                return String.valueOf(result);
            }
        } else {
            throw new RuntimeException("Числа должны быть в одном формате");
        }
    }

    public static void maxMinExceptions(int a, int b) {
        if (a > 10 || b > 10 || a < 0 || b < 0) {
            throw new RuntimeException("Некорректные данные, числа должны быть в диапазоне от 1 до 10");
        }
    }
}
