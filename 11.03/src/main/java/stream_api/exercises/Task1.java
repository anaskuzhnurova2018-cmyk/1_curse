package stream_api.exercises;

import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>(List.of(
                "Владивосток",
                "Власть",
                "Яблоко"
        ));

        //1 task

        Double res1 = list1.stream()
                .filter(s -> s.startsWith("Вл"))
                .map(s -> s.length())
                .mapToInt(x -> x)
                .average()
                .getAsDouble();

        System.out.println(res1);

        /*
        1 преобразовать список строк - отобрать строки, начинающиеся на "Вл", и вывести среднюю длину таких строк
        * */
    }
}
