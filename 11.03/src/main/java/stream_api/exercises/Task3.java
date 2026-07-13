package stream_api.exercises;

import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of(
                "Сыр",
                "Карп",
                "Приве",
                "Сорок",
                "Цапля"
        ));

        List<String> list2 = list.stream()
                .filter(a -> a.length() == 5)
                .sorted()
                .toList();

        System.out.println(list2);
    }
}

/*
3 список строк отфильтровать по длине, оставив только длины 5, отсортировать полученный результат
* */