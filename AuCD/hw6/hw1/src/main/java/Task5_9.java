import java.util.Queue;

public class Task5_9 {
    public static void main(String[] args) {
        int[] q1 = {1,3,5,7};
        int[] q2 = {2,4,6,8};

        int[] res = compare(q1, q2);
        for(int t : res) {
            System.out.println(t);
        }
    }

    public static int[] compare(int[] q1, int[] q2) {
        int[] result = new int[q1.length + q2.length];
        int i = 0, j = 0, k = 0; //i - текущий элемент в q1, j - текущий элемент в q2, k - текущая свободная ячейка в result

        while (i < q1.length && j < q2.length) { //пока в обоих массивах есть элементы
            if (q1[i] <= q2[j]) {
                result[k++] = q1[i++];
            } else {
                result[k++] = q2[j++];
            }
        }

        while (i < q1.length) { //если в q2 закончились элементы раньше, чем в q1, то оставшиеся элементы просто копируем, они уже отсортированы
            result[k++] = q1[i++];
        }

        while (j < q2.length) {
            result[k++] = q2[j++];
        }

        return result;
    }
}

/*
9. Даны две непустые очереди.
Элементы каждой из очередей упорядочены по возрастанию.
Объединить очереди в одну с сохранением упорядоченности элементов.
Оценить время работы программы.
* */