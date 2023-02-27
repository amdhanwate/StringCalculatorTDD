package org.example;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class StringCalculator {
    private int addCalledalledCount = 0;

    public int getAddCalledalledCount() {
        return addCalledalledCount;
    }

    public int getCalledCount() {
        return addCalledalledCount;
    }

    public ArrayList<String> getDelimiterAndNumberString(String numbers) {
        ArrayList<String> numberStringAndDelimiter = new ArrayList<>();
        if (numbers.startsWith("//")) {
            String delimiter = "";
            String[] splittedString = numbers.split("\n");
            String[] delimiters = splittedString[0].substring(2).trim().split("\\]\\[");
            for (String delim : delimiters) {
                delimiter += delim;
            }

            numberStringAndDelimiter.add(splittedString[1]);
            numberStringAndDelimiter.add(delimiter);

            return numberStringAndDelimiter;
        } else {
            numberStringAndDelimiter.add(numbers);
            numberStringAndDelimiter.add(",");
            return numberStringAndDelimiter;
        }
    }

    public int add(String numbers) throws Exception {
        this.addCalledalledCount += 1;
        String numberString = getDelimiterAndNumberString(numbers).get(0);
        String delimiter = getDelimiterAndNumberString(numbers).get(1);

        if (numberString.isEmpty()) return 0;
        else if (numberString.length() == 1) {
            if (Integer.parseInt(numberString) > 0) return Integer.parseInt(numberString);
            else throw new Exception("negatives not allowed: " + Integer.parseInt(numberString));
        } else {
            int sumNumbers = 0;
            ArrayList<Integer> negativeNumbers = new ArrayList<>();

            for (String number : numberString.replaceAll(delimiter, ",").split(",")) {
                int currentParsedNumber = Integer.parseInt(number);
                if (currentParsedNumber < 0) negativeNumbers.add(currentParsedNumber);
                else if (currentParsedNumber > 1000) {}
                else sumNumbers += currentParsedNumber;
            }

            if (!negativeNumbers.isEmpty()) throw new Exception("negatives not allowed: " + negativeNumbers);
            else return sumNumbers;
        }
    }
}
