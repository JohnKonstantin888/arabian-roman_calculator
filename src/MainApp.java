import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Converter converter = new Converter();
        String[] operations = {"+", "-", "/", "*"};
        String[] regexOperations = {"\\+", "-", "/", "\\*"};
        System.out.println("Введите выражение: ");
        Scanner console = new Scanner(System.in);
        String request = console.nextLine();
        //Определяем арифметическое действие:
        int operationIndex=-1;
        for (int i = 0; i < operations.length; i++) {
            if(request.contains(operations[i])){
                operationIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(operationIndex==-1){
            System.out.println("Некорректное выражение");
            return;
        }
        //Делим строчку по найденному арифметическому знаку
        String[] data = request.split(regexOperations[operationIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman) {
                //если римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } else {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            //выполняем с числами арифметическое действие
            int result;
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
                default:
                    result = a / b;
                    break;
            }
            //15->XV
            if(isRoman) {
                //если числа были римские, возвращаем результат в римском числе
                System.out.println(converter.intToRoman(result));
            } else {
                //если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        } else {
            System.out.println("Числа должны быть в одном формате");
        }
    }
}
