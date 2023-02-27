import org.example.StringCalculator;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();
    @Test
    public void addTest_EmptyString_ReturnZero() throws Exception {
        int expected = 0;
        int actual = stringCalculator.add("");

        assertEquals(expected, actual);
    }

    @Test
    public void addTest_SingleNumberString_ReturnSameSingleDigit() throws Exception {
        int expected = 5;
        int actual = stringCalculator.add("5");

        assertEquals(expected, actual);
    }

    @Test
    public void addTest_AnyNumberString_ReturnAdditionOfAll() throws Exception {
        int expected = 3;
        int actual = stringCalculator.add("1,2");

        assertEquals(expected, actual);
    }

    @Test
    public void addTest_NewlineBetweenNumbers_ReturnSumOfAll() throws Exception {
        int expected = 7;
        int actual = stringCalculator.add("1\n3,3");

        assertEquals(expected, actual);
    }

    @Test
    public void addTest_NegativeNumberInString_ThrowException() throws Exception {
        try {
            int actual = stringCalculator.add("-1");
        } catch (Exception e) {
            assertEquals("negatives not allowed: [-1]", e.getMessage());
        }
    }

    @Test
    public void addTest_MultipleNegativeNumbersInString_ThrowExceptionShowAllNumbers() throws Exception {
        try {
            int actual = stringCalculator.add("-1,-2,-2");
        } catch (Exception e) {
            assertEquals("negatives not allowed: [-1, -2, -2]", e.getMessage());
        }
    }

    @Test
    public void addTest_ReturnHowManyTimesAddMethodIsInvoked() {
        try {
            int actual = 3;
            int operation = stringCalculator.add("2,2") + stringCalculator.add("1,4") + stringCalculator.add("1,4");
            int expected = stringCalculator.getCalledCount();

            assertEquals(expected, actual);
        } catch (Exception e) {
            System.out.println("This should not happen");
        }
    }

    @Test
    public void addTest_StringContainsNumberGreaterThan1000_IgnoreThoseNumbers_ReturnSum() {
        try {
            int expected = 5;
            int actual = stringCalculator.add("2,1212,3");

            assertEquals(expected, actual);
        } catch (Exception e) {
            System.out.println("This should not happen");
        }
    }

    @Test
    public void addTest_StringContainsDelimiterOtherThanComma_ReturnSumUsingTheDelimiter() throws Exception {
        int expected = 10;
        int actual = stringCalculator.add("//;\n1;4;5");

        assertEquals(expected, actual);
    }

    @Test
    public void addTest_StringContainsAnyLengthDelimiter_ReturnSumUsingTheDelimiter() throws Exception {
        int expected = 10;
        int actual = stringCalculator.add("//aa\n1aa4aa5");

        assertEquals(expected, actual);
    }
}
