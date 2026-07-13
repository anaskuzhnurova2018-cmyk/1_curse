package stream_api.exercises;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(
                12, 3, 56, 123, 1, 32, 81
        ));

        List<Double> newList = list.stream()
                .sorted((a, b) -> b - a)
                .map(a -> Math.sqrt(a))
                .toList();

        System.out.println(newList);
    }
}

/*
2 список целых чисел отсортировать по убыванию, преобразовать в список действительных чисел из корней исходных чисел
* */