import java.util.Arrays;
import java.util.Scanner;

public class Task15_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] current = new int[n];
        boolean[] used = new boolean[n + 1];
        generate(n, current, used, 0);
    }
    public static void generate(int n, int[] current, boolean[] used, int pos) {
        if (pos == n) System.out.println(Arrays.toString(current));
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                current[pos] = i;
                generate(n, current, used, pos + 1);
                used[i] = false; //отпускаем число, чтобы оно могло учавствовать и в других ветках рекурсии
            }
        }
    }
}

//Методические указания: Предложить переборный алгоритм решения задачи, используя метод ветвей и границ.
//4. Напечатать все перестановки чисел 1..n. Сложность O(n!)

/*
Метод ветвей и границ: если видишь, что ветка точно не даст результата лучше уже найденного - сразу руби ее и не трать время
ВиГ заранее прикидывает перспективы и отсекает бесперспективные направления
Три кита метода:
1) ветвление (разбиваем задачу на подзадачи, строя дерево вариантов)
2) оценка (для каждой ветки считаем оптимистичный прогноз: лучше/хуже какого значения эта ветка не может стать)
3) отсечение (сравниваем оценку с текущем рекордом, не проверяем заведомо плохие варианты)
* */