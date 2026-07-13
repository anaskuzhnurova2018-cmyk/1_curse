package ModuleTests.hw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMergeSort {
    @Test
    public void testMergeSort() {
        int[] array = {1, 99, 100, -5, 78, 2, 55, 34, 0, 200};
        int[] expectedArray = {-5, 0, 1, 2, 34, 55, 78, 99, 100, 200};

        MergeSort sorter = new MergeSort();

        sorter.mergeSort(array, 0, array.length - 1);

        int expectedSize = expectedArray.length;
        int actualSize = array.length;

        Assertions.assertEquals(expectedSize, actualSize, "Размеры массивов не сошлись");
        Assertions.assertArrayEquals(expectedArray, array, "Сортировка работает неверно");
    }
}
