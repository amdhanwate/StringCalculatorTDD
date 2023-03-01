import static org.assertj.core.api.Assertions.*;
import org.example.StringCalculator;
import org.junit.Test;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void addTest_EmptyString_ReturnZero() {
        int expected = 0;
        int actual = 0;
        try {
            actual = stringCalculator.add("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_SingleNumberString_ReturnSameSingleDigit() {
        int expected = 5;
        int actual = 0;
        try {
            actual = stringCalculator.add("5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_AnyNumberString_ReturnAdditionOfAll() {
        int expected = 3;
        int actual = 0;
        try {
            actual = stringCalculator.add("1,2");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_NewlineBetweenNumbers_ReturnSumOfAll() {
        int expected = 7;
        int actual = 0;
        try {
            actual = stringCalculator.add("1,\n3,3");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_NegativeNumberInString_ThrowException() {
        assertThatThrownBy(() -> stringCalculator.add("-1,1,3")).hasMessage("negatives not allowed: [-1]");
    }

    @Test
    public void addTest_MultipleNegativeNumbersInString_ThrowExceptionShowAllNumbers() {
        assertThatThrownBy(() -> stringCalculator.add("-1,-2,-2")).hasMessageStartingWith("negatives");
    }

    @Test
    public void addTest_ReturnHowManyTimesAddMethodIsInvoked() {
        int actual = 3;
        try {
            int operation = stringCalculator.add("2,2") + stringCalculator.add("1,4") + stringCalculator.add("1,4");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int expected = stringCalculator.getCalledCount();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_StringContainsNumberGreaterThan1000_IgnoreThoseNumbers_ReturnSum() {
        int expected = 5;
        int actual = 0;
        try {
            actual = stringCalculator.add("2,1212,3");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_StringContainsDelimiterOtherThanComma_ReturnSumUsingTheDelimiter() {
        int expected = 10;
        int actual = 0;
        try {
            actual = stringCalculator.add("//[;]\n1;4;5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_StringContainsAnyLengthDelimiter_ReturnSumUsingTheDelimiter() {
        int expected = 6;
        int actual = 0;
        try {
            actual = stringCalculator.add("//[;]\n1;2;3");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(actual).isEqualTo(expected);
    }
}