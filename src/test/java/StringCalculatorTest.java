import static org.assertj.core.api.Assertions.*;
import org.example.StringCalculator;
import org.junit.Test;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void addTest_EmptyString_ReturnZero() throws Exception {
        int expected = 0;
        int actual = stringCalculator.add("");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_SingleNumberString_ReturnSameSingleDigit() throws Exception {
        int expected = 5;
        int actual = stringCalculator.add("5");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_AnyNumberString_ReturnAdditionOfAll() throws Exception {
        int expected = 3;
        int actual = stringCalculator.add("1,2");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_NewlineBetweenNumbers_ReturnSumOfAll() throws Exception {
        int expected = 7;
        int actual = stringCalculator.add("1,\n3,3");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_NegativeNumberInString_ThrowException() {
        assertThatThrownBy(() -> stringCalculator.add("-1,1,3")).hasMessage("negatives not allowed: [-1]");
    }

    @Test
    public void addTest_MultipleNegativeNumbersInString_ThrowExceptionShowAllNumbers() throws Exception {
        assertThatThrownBy(() -> stringCalculator.add("-1,-2,-2")).hasMessageStartingWith("negatives");
    }

    @Test
    public void addTest_ReturnHowManyTimesAddMethodIsInvoked() throws Exception {
        int actual = 3;
        int operation = stringCalculator.add("2,2") + stringCalculator.add("1,4") + stringCalculator.add("1,4");
        int expected = stringCalculator.getCalledCount();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_StringContainsNumberGreaterThan1000_IgnoreThoseNumbers_ReturnSum() throws Exception {
        int expected = 5;
        int actual = stringCalculator.add("2,1212,3");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_StringContainsDelimiterOtherThanComma_ReturnSumUsingTheDelimiter() throws Exception {
        int expected = 10;
        int actual = stringCalculator.add("//[;]\n1;4;5");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addTest_StringContainsAnyLengthDelimiter_ReturnSumUsingTheDelimiter() throws Exception {
        int expected = 6;
        int actual = stringCalculator.add("//[;]\n1;2;3");

//        assertEquals(expected, actual);
        assertThat(actual).isEqualTo(expected);
    }
}