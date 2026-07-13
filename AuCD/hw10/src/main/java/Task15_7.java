import java.util.Arrays;
import java.util.Scanner;

public class Task15_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        generate(n, array, n, 1, 0);
    }
    public static void generate(int n, int[] array, int remaining, int minPart, int depth) { //depth - текущая позиция в массиве, minPart - с какого числа начинать перебор на этом уровне
        if (remaining == 0) {
            for (int j = 0; j < depth; j++) {
                System.out.print(array[j] + " ");
            }
            System.out.println();
        }
        for (int i = minPart; i <= remaining; i++) { //начинаем с minPart, чтобы сохранить неубывающий порядок -> избежать повторов
            array[depth] = i;
            generate(n, array, remaining - i, i, depth + 1);
        }
    }
}

/*
7. Перечислить все разбиения целого положительного числа n на целые положительные слагаемые
(разбиения, отличающиеся лишь порядком слагаемых, считаются за одно).
(Пример: n=4|, разбиения 1+1+1+1|, 2+1+1|, 2+2|, 3+1|, 4|.).
Сложность О(n!)
* */