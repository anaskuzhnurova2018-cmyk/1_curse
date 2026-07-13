package booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainBooking {
    public static void main(String[] args) {
        //считаем файл в объект BookingData
        ObjectMapper mapper = new ObjectMapper();

        try {
            BookingData data = mapper.readValue(new File("C:\\ITISproga\\season26\\13.03\\booking.json"), BookingData.class);
            //data.getBookings().forEach(b -> System.out.println(b.getId()));
            /*//task 1
            long countMale =  data.getBookings().stream()
                    .filter(b -> b.getHotel().getId() == 150)
                    .filter(b -> b.getPerson().getGender().equals("Male"))
                    .count();

            long countFemale =  data.getBookings().stream()
                    .filter(b -> b.getHotel().getId() == 150)
                    .filter(b -> b.getPerson().getGender().equals("Female"))
                    .count();

            System.out.println("Male: " + countMale + " Female: " + countFemale);
*/
            /*//task2
            List<String> listOfRouteAreas = data.getBookings().stream()
                    .map(b -> b.getPerson().getFromcity()) // .map() преобразует каждый элемент потока в другой объект по заданной функции: превращает Booking в String; используем, когда нужно извлечь поле из объекта, преобразовать тип данных, применить вычисление к каждому элементу, подготовить данные для след операции
                    .sorted()
                    .toList();
            for (String c : listOfRouteAreas) {
                System.out.println(c);
            }*/

            /*//task 3
            long count = data.getBookings().stream()
                    .map(b -> b.getArrivaldate())
                    .filter(b -> b.startsWith("2021-12"))
                    .count();
            System.out.println("Кол-во бронирований за декабрь 2021 = " + count);*/

            /*// task 4
            Map<String, Integer> hotelCount = data.getBookings().stream()
                    .collect(Collectors.toMap(
                            b -> b.getHotel().getName(), //как формируется ключ
                            b -> 1, //как формируется значение
                            (a, b) -> a + b //разрешаем конфликт разных значений для одного ключа
                    ));
            for (Map.Entry<String, Integer> entry : hotelCount.entrySet()) {
                String hotelName = entry.getKey();
                int count = entry.getValue();
                System.out.println(hotelName + " - " + count);
            }*/

            /*//task 5
            Map<String, Integer> cityCount = data.getBookings().stream()
                    .collect(Collectors.toMap(
                            b -> b.getPerson().getFromcity(),
                            b -> 1,
                            (a, b) -> a + b
                    ));

            for (Map.Entry<String, Integer> entry : cityCount.entrySet()) {
                String cityName = entry.getKey();
                int count = entry.getValue();
                System.out.println(cityName + " - " + count);
            }
             */

            /*//task 6
            long count = data.getBookings().stream()
                    .map(b -> b.getPerson().getFromcity())
                    .filter(b -> b.startsWith("Город Санкт-Петербург"))
                    .count();

            System.out.println("Кол-во бронирований людей из Петербурга = " + count);
             */
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
1 вывести кол-во мужчин/женщин, бронирующих в гостинецес id = 150; +
2 вывести список населенных пунктов в отсортированном виде, из которых прибыли гости +
3 вывести кол-во бронирований за декабрь 2021 года +
4 вывести таблицу: название гостиницы - кол-во бронирований +
5 вывести табл: название населенного пункта - колв-во бронированний +
6 вывести кол-во бронированний людей из Петербурга
* */