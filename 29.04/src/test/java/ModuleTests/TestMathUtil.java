package ModuleTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class TestMathUtil {
    @Test //аннотация инициирует автоматическую проверку
    public void testSum() {
        // проверяем результат
        Assertions.assertEquals(0,
                MathUtil.sum(0, 0));

        Assertions.assertEquals(-10,
                MathUtil.sum(-3, -7));

        Assertions.assertEquals(0,
                MathUtil.sum(-3, 3));

        Assertions.assertEquals(5,
                MathUtil.sum(3, 2));
    }


    @ParameterizedTest //аннотация инициирует автоматическую проверку
    //
    @CsvSource({
            "5, 5, 10", //1-й набор аргументов
            "1, 2, 3", //2-й набор аргументов
            "49, 51, 100", //3-й набор аргументов
            "1, 100, 101" //4-й набор аргументов
    }) //порядое аргмуентов в сигнатуре длжен соотвествовать набору данных
    public void testSumEx(int a, int b, int expectedResult) {
        // проверяем результат
        Assertions.assertEquals(expectedResult,
                MathUtil.sum(a, b));
    }


    @Test
    public void testCompare() {
        Assertions.assertEquals(0,
                MathUtil.compare(2, 2));

        Assertions.assertEquals(3,
                MathUtil.compare(5, 2));

        Assertions.assertEquals(-3,
                MathUtil.compare(1, 4));
    }

    @Test
    public void testSort () {
        Assertions.assertArrayEquals(new int[] {1, 2, 5, 4, 3},
                MathUtil.sort(new int[] {1, 2, 5, 4, 3}));
    }

    @Test
    public void testDivideException() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> { MathUtil.divide(4,0);});
    }
}
