package NewsApp;

import java.util.LinkedList;

public class SupportMethod {
    public String breakingString(String inputString, int numberOfCharacterPerLine) {
        char[] charStringTmp = inputString.toCharArray();
        LinkedList<Character> charString = new LinkedList<>();
        // add all character to linkedlist => add breakline
        for (int i = 0; i < inputString.length(); i++) {
            charString.add(charStringTmp[i]);
        }

        // check linked each 20 character => breakline
        int count = 0;
        for (int i = 0; i < charString.size(); i++) {
            if (count == numberOfCharacterPerLine) {
                charString.add(i, '\n');
                count = 0;
            }else if (charString.get(i) == '\n'){
                count = 0;
            }else {
                count += 1;
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
