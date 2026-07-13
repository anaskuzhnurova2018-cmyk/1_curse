import java.util.Arrays;
import java.util.Comparator;

public class MinNumberFromDigits {

    public static String getMinNumber(int[] digits) {
        if (digits == null || digits.length == 0) {
            return "";
        }

        // 1. Преобразуем примитивные int в Integer для работы с компаратором
        Integer[] d = new Integer[digits.length];
        for (int i = 0; i < digits.length; i++) {
            d[i] = digits[i];
        }

        // 2. Сортируем с использованием специального компаратора
        // Хотя для одиночных цифр достаточно a - b,
        // мы используем строковое сравнение конкатенаций для универсальности принципа.
        Arrays.sort(d, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String order1 = String.valueOf(a) + String.valueOf(b);
                String order2 = String.valueOf(b) + String.valueOf(a);
                return order1.compareTo(order2);
            }
        });

        // 3. Обработка ведущих нулей
        // Если после сортировки первый элемент '0', значит, все элементы '0'
        // (так как 0 всегда будет первым при такой сортировке, если есть другие цифры, они будут позже)
        // Но нам нужно найти первую НЕ нулевую цифру и поставить её в начало.

        // Находим индекс первого ненулевого элемента
        int firstNonZeroIndex = -1;
        for (int i = 0; i < d.length; i++) {
            if (d[i] != 0) {
                firstNonZeroIndex = i;
                break;
            }
        }

        // Если все нули
        if (firstNonZeroIndex == -1) {
            return "0";
        }

        // Формируем результат:
        // Сначала берем первый ненулевой элемент
        StringBuilder sb = new StringBuilder();
        sb.append(d[firstNonZeroIndex]);

        // Затем добавляем все элементы до него (это нули)
        for (int i = 0; i < firstNonZeroIndex; i++) {
            sb.append(d[i]);
        }

        // Затем добавляем все элементы после него
        for (int i = firstNonZeroIndex + 1; i < d.length; i++) {
            sb.append(d[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[] digits1 = {3, 3, 2, 1, 0};
        System.out.println("Минимальное число: " + getMinNumber(digits1)); // Ожидаем: 10233

        int[] digits2 = {0, 0, 0, 1};
        System.out.println("Минимальное число: " + getMinNumber(digits2)); // Ожидаем: 1000

        int[] digits3 = {0, 0, 0};
        System.out.println("Минимальное число: " + getMinNumber(digits3)); // Ожидаем: 0
    }
}