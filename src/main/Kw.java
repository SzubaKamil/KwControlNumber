package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kw {

    private static Map<String, Integer> charMap;
    private static ArrayList<Integer> weight;
    private String[] kwNumber;

    public Kw() {
        kwNumber = new String[0];
        charMap = new HashMap<>();
        weight = new ArrayList<>();
        createMap();
        createWeight();
    }

    public int getControlDigit(String kwNumber) {
            this.kwNumber = kwNumber.split("");
            for (String i : this.kwNumber) {
                if (!charMap.containsKey(i)) {
                    return -1; // incorrect char
                }
            }
            if (this.kwNumber.length < 12) {
                return -2; // to short
            } else if (this.kwNumber.length > 12) {
                return -3; // to long
            }
        return countControlDigit();
    }

    public String printKwNumber() {
        String kwNumberString = "";
        for (int i = 0; i < kwNumber.length; i++) {
            kwNumberString += kwNumber[i];
        }
        return kwNumberString;
    }

    private int countControlDigit() {
        int charNumber = 0;
        int sum = 0;

        for (String i : kwNumber) {
            sum = sum + (charMap.get(i) * weight.get(charNumber));
            charNumber++;
        }
        return sum % 10;
    }

    public int isNextControlDigit(boolean next) {
        if (this.kwNumber.length == 0) {
            return -1; // brak kw w pamiecie
        }

        int goNext = -1;
        int lastEightDigit;
        String stringLastEightDigit = "";

        if (next) {
            goNext = 1;
        }

        for (int i = 4; i < 12; i++) {
            stringLastEightDigit += this.kwNumber[i];
        }

        lastEightDigit = Integer.parseInt(stringLastEightDigit) + goNext;
        stringLastEightDigit = String.format("%08d", lastEightDigit);
        String[] arrayLastEight = stringLastEightDigit.split("");

        for (int i = 4; i < 12; i++) {
            this.kwNumber[i] = arrayLastEight[i - 4];
        }

        return countControlDigit();
    }

    private static void createMap() {
        charMap.put("X", 10);

        for (int i = 0; i < 10; i++) {
            charMap.put("" + i, i);
        }
        char initialLetter = 'A';

        for (int i = 11; i < 34; i++) {
            if (initialLetter != 'Q' && initialLetter != 'V' && initialLetter != 'X') {
                charMap.put("" + initialLetter, i);
            } else {
                i--;
            }
            initialLetter++;
        }
    }

    private static void createWeight() {
        int[] toAdd = {1, 3, 7};
        int index = 0;

        for (int i = 0; i < 12; i++) {
            weight.add(toAdd[index]);
            if (index == toAdd.length - 1) {
                index = 0;
            } else {
                index++;
            }
        }
    }

}
