import java.util.Arrays;
import java.util.Scanner;

public class Task15_8 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean[] usedCols = new boolean[n];
        int[] posRow = new int[k];
        int[] posCol = new int[k];

        backTrack(n, k, 0, 0, usedCols, posRow, posCol);

    }
    public static void backTrack(int n, int k, int row, int placed, boolean[] usedCols, int[] posRow, int[] posCol) { //row - текущая строка, placed - сколько ладей уже поставили, usedCols - показывает, что столбец занят
        if (placed == k) {
            for (int j = 0; j < placed; j++) {
                System.out.print("[" + posRow[j] + "," + posCol[j] + "] ");
            }
            System.out.println();
            return;
        }
        if (row == n) return; //доска кончилась
        backTrack(n, k, row + 1, placed, usedCols, posRow, posCol); //пропускаем текущую строку
        for (int i = 0; i < n; i++) { //i - столбец
            if (!usedCols[i]) {
                usedCols[i] = true;
                posRow[placed] = row;
                posCol[placed] = i;
                backTrack(n, k, row + 1, placed + 1, usedCols, posRow, posCol);
                usedCols[i] = false;
            }
        }
    }
}

/*
8. Перечислить все способы расстановки k ладей на шахматной доске n × n,
при которых они не бьют друг друга.
k<n
Сложность О(n!/(n-k)!)

не бьют друг друга: в одной строке не более одной ладьи
в одном столбце не более одной ладьи
* */