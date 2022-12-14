package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringAddCalculatorTest {
    @DisplayName("빈 문자열 또는 null 값이 입력되면 0이 반환된다")
    @ParameterizedTest
    @NullAndEmptySource
    public void splitAndSum_null_and_empty_input(String input) {
        int actual = StringAddCalculator.splitAndSum(input);
        assertThat(actual).isEqualTo(0);
    }

    @DisplayName("숫자 하나가 문자열로 입력할 경우 해당 숫자를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    public void splitAndSum_one_number(String input) {
        int actual = StringAddCalculator.splitAndSum(input);
        int expect = Integer.parseInt(input);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("숫자 두개를 컴마(,) 구분자로 입력할 경우 두 숫자의 합을 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"1,2:3", "10,20:30", "99,1:100"}, delimiter = ':')
    public void splitAndSum_two_number_by_comma(String input, int expect) {
        int actual = StringAddCalculator.splitAndSum(input);
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("구분자로 콜론(:)을 사용할 수 있다")
    @Test
    public void splitAndSum_colon_delimiter() {
        int actual = StringAddCalculator.splitAndSum("1,2:3");
        assertThat(actual).isEqualTo(6);
    }

    @DisplayName("//와 \\n 문자 사이에 커스텀 구분자를 지정할 수 있다")
    @Test
    public void splitAndSum_custom_delimiter() {
        int actual = StringAddCalculator.splitAndSum("//;\n1;2;3");
        assertThat(actual).isEqualTo(6);
    }

    @DisplayName("음수를 전달할 경우 RuntimeException 예외가 발생한다")
    @Test
    public void splitAndSum_negative_exception() {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("-1,2,3"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("입력값은 양수여야 합니다");
    }
}
