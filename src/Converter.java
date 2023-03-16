import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Converter {
    Map<Character, Integer> romanKeyMap = new HashMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();
    public int toArabian(String number) {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);

        int end = number.length() - 1;
        char[] array = number.toCharArray();
        int arabian;
        int result = romanKeyMap.get(array[end]);
        for (int i = end - 1; i >= 0; i--) {
            arabian = romanKeyMap.get(array[i]);

            if (arabian < romanKeyMap.get(array[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }
        return result;
    }

    public String toRoman(int number) {
        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");

        String roman = "";
        int arabianKey;
        do {
            arabianKey = arabianKeyMap.floorKey(number);
            roman += arabianKeyMap.get(arabianKey);
            number -= arabianKey;
        } while (number != 0);
        return roman;
    }

}
