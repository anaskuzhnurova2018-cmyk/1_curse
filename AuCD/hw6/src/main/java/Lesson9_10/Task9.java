package Lesson9_10;

import java.util.Arrays;

public class Task9 {
    public static void main(String[] args) {
        //int[] list = {1, 2, 0, 200, 100, 65, 10};
        int[] list = {3, 30, 34, 5, 9};

        System.out.println(changePlaces(list));
    }

    public static String changePlaces(int[] list) {
        String[] strs = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            strs[i] = String.valueOf(list[i]);
        }
        //сложность N

        Arrays.sort(strs, (a, b) -> {
            return (b + a).compareTo(a + b);
        }); //используется Timsort, сложность NlogN

        StringBuilder sb = new StringBuilder(); //строки - незименяемый тип даннх, если бы делали str+=..., то пришлось бы создавать кучу промежуточных результатов, StringBuilder использует массив (если место есть, то просто добавляет внутрь массива, если места нет, то увеличивает внутренний массив)
        for (String s : strs) {
            sb.append(s);
        }

        String res = sb.toString();

        return res;
    }
}

/*
9. Дан список неотрицательных чисел.
Измените их порядок так, чтобы если их после этого выписать в строку,
то получилось максимальное из возможных чисел.
Пример: дан массив [3, 30, 34, 5, 9].
Максимальное возможное число можно получить 9534330. Сложность O(n log n)
* */