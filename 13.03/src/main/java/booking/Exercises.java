package booking;

//18.03

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercises {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            BookingData data = mapper.readValue(new File("C:\\ITISproga\\season26\\13.03\\booking.json"), BookingData.class);

            /*//task 1
            String hotel = data.getBookings().stream()
                    .filter(a -> a.getArrivaldate().startsWith("2022-01"))
                    .collect(Collectors.toMap(
                            k -> k.getHotel().getName(),
                            v -> 1,
                            (a, b) -> a + b
                    ))
                    .entrySet().stream()
                    .max((a, b) -> a.getValue() - b.getValue()).get().getKey();

            System.out.println(hotel);
             */

            //task 2
            /*
            data.getBookings().stream()
                    .filter(a -> {
                        if (a.getPerson().getBirthdate() == null) return false;
                        String str = a.getPerson().getBirthdate().substring(0, 4);
                        if (str == null) {
                            return false;
                        }
                        Integer i = Integer.parseInt(str);
                        if (i >= 1973 || i <= 1983) {
                            return true;
                        } else {
                            return false;
                        }
                    })
                    .collect(Collectors.toMap(
                            b -> b.getPerson().getFromcity(),
                            b -> 1,
                            (a, b) -> a + b
                    )).forEach((a ,b) -> System.out.println(a));
             */

            /*//task 3

            data.getBookings().stream()
                    .filter(a -> {
                        if (a.getPerson().getBirthdate() == null) return false;
                        if (a.getArrivaldate() == null) return false;
                        String str = a.getPerson().getBirthdate().substring(0, 4);
                        if (str == null) {
                            return false;
                        }
                        Integer i = Integer.parseInt(str);
                        String str1 = a.getArrivaldate().substring(0, 4);
                        Integer d = Integer.parseInt(str1);
                        if (d - i <= 14 && d - i >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    })
                    .map(a -> a.getHotel().getName())
                    .collect(Collectors.toSet())
                    .forEach(a -> System.out.println(a));
             */

            /*//task 4

            data.getBookings().stream()
                    .collect(Collectors.toMap(
                            b -> b.getPerson(),
                            b -> 1,
                            (a, b) -> a + b
                    )).entrySet().stream()
                    .filter(a -> a.getValue() > 3)
                    .forEach(a -> System.out.println(a.getKey()));
             */

            /*//task 5

            data.getBookings().stream()
                    .filter(a -> a.getPerson().getGender() != null && a.getPerson().getGender().equals("Female"))
                    .filter(a -> a.getPerson().getFromcity() != null && a.getPerson().getFromcity().startsWith("Город Санкт-Петербург"))
                    .filter(a -> check(a.getArrivaldate(), a.getDeparturedate()))
                    .map(a -> a.getHotel().getName())
                    .forEach(a -> System.out.println(a));
             */

            /*//task 7
            data.getBookings().stream()
                    .filter(a -> a.getHotel().getId() == 123)
                    .filter(a -> a.getPerson().getFromcity().equals("Самарская область"))
                    .filter(a -> a.getArrivaldate().startsWith("2021-03") || a.getArrivaldate().startsWith("2021-05"))
                    .forEach(a-> System.out.println(a.getPerson()));
             */

            //task 6
            //вывести таблицу возрастная группа - количество броней
            //(группы: 1 - 0-14, 2 - 15-18, 3 - 19-25, 4 - 26-35, 5 - 36-45, 6 - 45-...)
            data.getBookings().stream()
                    .collect(Collectors.groupingBy(
                            b -> getAgeGroup(b.getPerson().getBirthdate()),
                            Collectors.counting()
                    )).forEach((group, count) -> System.out.println("Группа " + group + ": " + count + " броней"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean check(String a, String s) {
        if (a == null || s == null) return false;
        int a1 = Integer.parseInt(a.substring(5, 7));
        int s1 = Integer.parseInt(s.substring(5, 7));
        return (a1 <= 3 && s1 >= 2);
    }

    public static int getAgeGroup(String s) {
        if (s == null) return -1;
        int s1 = Integer.parseInt(s.substring(0, 4));
        if (s1 > 2022) return -1;
        if (s1 >= 2008) {
            return 1;
        } else if (s1 >= 2004) {
            return 2;
        } else if (s1 >= 1997) {
            return 3;
        } else if( s1 >= 1987) {
            return 4;
        } else if (s1 >= 1977) {
            return 5;
        } else {
            return 6;
        }
    }
}

/*
1. Найти гостиницу с максимальным числом броней за январь 2023 года
2. Вывести список городов из которых прибыли мужчины от 35 до 45 лет (смотрим только год рождения)
3. вывести список гостиниц (названия) в которых проживали дети до 14 лет
(сравниваем год регистрации и год рождения)
4. вывести список людей с количеством бронирований больше 3
5. Вывести гостиницы в которых проживали женщины из Санкт-Петербурга
в период с февраля по март 2023 года (использовать несколько фильтров)
6. вывести таблицу возрастная группа - количество броней
(группы: 1 - 0-14, 2 - 15-18, 3 - 19-25, 4 - 26-35, 5 - 36-45, 6 - 45-...)
7. вывести список проживающих в гостинице с id=123, приехавших из Самарской области с апреля по май 2023г.
* */