package org.example;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class StringCalculator {
    private int addCallCount = 0;

    public int getCalledCount() {
        return addCallCount;
    }

    public int add(String numbers) throws Exception {
        this.addCallCount += 1;
        String numberString = getNumberStringWithDelimitersReplaced(numbers);

        if (numberString.isEmpty()) return 0;
        else if (numberString.length() == 1) {
            if (Integer.parseInt(numberString) > 0) return Integer.parseInt(numberString);
            else throw new Exception("negatives not allowed: " + Integer.parseInt(numberString));
        } else {
            int sumNumbers = 0;
            ArrayList<Integer> negativeNumbers = new ArrayList<>();

            for (String number : numberString.split(",")) {
                int currentParsedNumber = Integer.parseInt(number);
                if (currentParsedNumber < 0) negativeNumbers.add(currentParsedNumber);
                else if (currentParsedNumber <= 1000) sumNumbers += currentParsedNumber;
            }

            if (!negativeNumbers.isEmpty()) throw new Exception("negatives not allowed: " + negativeNumbers);
            return sumNumbers;
        }
    }

    public String getNumberStringWithDelimitersReplaced(String numbers) {
        String numberStringWithReplacedDelimiter = "";
        String numberString = numbers.substring(numbers.indexOf("]\n")+1).replace("\n","");
        if (numbers.startsWith("//")) {
            String delimittedString = numbers.substring(numbers.indexOf("["), numbers.indexOf("\n"));

            for (String delimit : delimittedString.replace("[","").split("]")) {
                numberString = numberString.replaceAll(Pattern.quote(delimit), ",");
            }

            numberString = numberString.replaceAll("\n", "");
            numberStringWithReplacedDelimiter = numberString;

        } else {
            numbers = numbers.replaceAll("\n", "");
            numberStringWithReplacedDelimiter = numbers;
        }
        return numberStringWithReplacedDelimiter;
    }
}