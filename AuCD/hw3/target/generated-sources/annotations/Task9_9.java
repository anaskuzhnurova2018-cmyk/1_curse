import java.util.Arrays;

public class Task9_9 {
    public static void main(String[] args) {
        Integer[] a = {301, 234, 342, 511, 989, 97, 30};
        String[] s = new String[a.length];

        for (int i = 0; i < a.length; i++) { //превращаем каждое число в строку
            s[i] = Integer.toString(a[i]);
        }

        Arrays.sort(s, (s1, s2) -> {
            String res1 = s1 + s2;
            String res2 = s2 + s1;

            return res2.compareTo(res1);
        });

        StringBuilder result = new StringBuilder();
        for (String r : s) {
            result.append(r);
        }

        System.out.println(result);
    }
}

/*
Дан список неотрицательных чисел.
Измените их порядок так, чтобы если их после этого выписать в строку,
то получилось максимальное из возможных чисел.
Пример: дан массив [3, 30, 34, 5, 9].
Максимальное возможное число можно получить 9534330.
Сложность O(n log n)
* */