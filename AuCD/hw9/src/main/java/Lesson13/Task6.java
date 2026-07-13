package Lesson13;

import java.util.ArrayList;
import java.util.List;

public class Task6 {

    public static void main(String[] args) {
        int[] weights = {2, 3, 5, 7};
        int S = 10;

        List<Integer> answer = findItems(weights, S);

        if (answer == null) {
            System.out.println("Решения нет");
        } else {
            System.out.println("найдены предметы: ");
            for (Integer ans : answer) {
                System.out.println(ans); //выводятся индексы предметов
            }
        }
    }

    public static List<Integer> findItems(int[] weights, int S) {
        int k = weights.length;
        boolean[][] dp = new boolean[k + 1][S + 1]; //строки i (предметы), столбцы j (сумма)

        dp[0][0] = true;

        for (int i = 1; i <= k; i++) { //перебираем предметы
            int w = weights[i - 1]; //вес текущего предмета
            for (int j = 0; j <= S; j++) { //для каждого предмета перебираем все возможные суммы
                //не берем предмет
                dp[i][j] = dp[i-1][j]; //если не берем предмет, то возможность набрать сумму j такая же, как если бы мы рассматривали только предыдущие i-1 предметов
                //берем предмет, если можно
                if (j >= w && dp[i-1][j-w]) { //должны уметь набрать остаток, используя предыдущие предметы
                    dp[i][j] = true;
                }

            }
        }

        if (!dp[k][S]) {
            return null; //нет решения
        }

        //восстановление пути
        List<Integer> result = new ArrayList<>();
        int currentSum = S;
        for (int i = k; i > 0; i--) {
            //если значение изменилось по сравнению с предыдущей строкой, то значит предмет взяли
            if (dp[i][currentSum]) { //если одно true, а предыдущее false, значит без этого предмета сумму нельзя было набрать
                if (!dp[i-1][currentSum]) {
                    result.add(i - 1); //добавляем индекс предмета
                    currentSum -= weights[i-1];
                }
            }

            if (currentSum == 0) {
                break;
            }
        }
        return result;
    }
}

/*
6. Имеется k неделимых предметов.
Для каждого  предмета  известна его масса (в кг.).
Величины массы  являются  натуральными числами.
Определить, существует ли несколько предметов,
суммарная масса предметов которого ровно S кг.
Если такой набор существует,
то требуется определить список предметов в наборе.
(Сложность O(S*k)).
* */