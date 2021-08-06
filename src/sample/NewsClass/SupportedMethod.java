package sample.NewsClass;

import java.util.LinkedList;

public class SupportedMethod {
    public String breakingString(String inputString, int numberOfCharacterPerLine) {
        if (inputString.length() <= numberOfCharacterPerLine) {
            return inputString;
        }
        char[] charStringTmp = inputString.toCharArray();
        LinkedList<Character> charString = new LinkedList<>();
        // add all character to linkedlist => add breakline
        for (int i = 0; i < inputString.length(); i++) {
            charString.add(charStringTmp[i]);
        }

        // check linked each 20 character => breakline
        int count = 0;
        int lastSpace = 0;
        charString.add(0, ' ');
        for (int i = 0; i < charString.size(); i++) {
            if (count == numberOfCharacterPerLine) {
                if (charString.get(i + 1) != ' ') {
                    charString.add(lastSpace, '\n');
                    count = i - lastSpace;
                }else {
                    charString.add(i, '\n');
                    count = 0;
                }
            }else {
                count += 1;
            }
            if (charString.get(i) == ' ') {
                lastSpace = i;
            }
        }
        // return string
        String s = "";
        for (int i = 0; i < charString.size(); i++) {
            s = s + charString.get(i);
        }
        return s;
    }
}
