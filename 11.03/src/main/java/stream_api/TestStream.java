package stream_api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//Optional - используется как обертка для результата

public class TestStream {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(3, 6, 5, 11, 18, 0, 9, 22));

        //1 Найти максимум (сразу применяется финализирующий метод)
        Integer max = numbers.stream().max((a ,b) -> a - b).get();
        System.out.println(max);

        //2 Найти минимум
        Integer min = numbers.stream().min((a ,b) -> a - b).get();
        System.out.println(min);

        //3 конвеерный метод используем: выбрать четный числа и найти их среднее
        Double avg = numbers.stream()
                .filter(a -> a % 2 == 0) //применяем фильтрацию (конвеерный)
                .mapToInt(a -> a)//преобразуем в целочисленный стрим (тривальное преобразование)
                .average().getAsDouble(); //находим среднее (финализирующий метод)

        System.out.println(avg);

        //преобразование списка целых чисел в список строк
        List<String> list = numbers.stream()
                .map(a -> String.valueOf(a)) //описываем способ преобразования числа в строку
                .toList(); // финализирующий метод

        System.out.println(list);
    }
}

