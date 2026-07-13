package stream_api.exercises;

import java.util.ArrayList;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        List<Double> list = new ArrayList<>(List.of(
                3.4, 4.57, 5.67, 33.3, 45.0
        ));

        List<Double> list2 = list.stream()
                .sorted((a, b) -> String.valueOf(a).toCharArray()[String.valueOf(a).length() - 1] - String.valueOf(b).toCharArray()[String.valueOf(b).length() - 1])
                .toList();

        System.out.println(list2);
    }

}

/*
4 список действительных чисел отсортировать по последнему разряду и вывести в консоль
* */