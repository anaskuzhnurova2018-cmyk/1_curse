package KR1_VAR2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\ITISproga\\season26\\01.04\\src\\main\\java\\KR1_VAR2\\varient2.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<Trip> trips = reader.lines()
                    .map(s -> new Trip(s))
                    .collect(Collectors.toList());

            //task 4
            /*Map<String, Integer> res = trips.stream()
                    .collect(Collectors.toMap(
                            n -> n.getAuto(),
                            n -> 1,
                            (n, c) -> n + c
                    ));

            for(Map.Entry<String, Integer> entry : res.entrySet()) {
                System.out.println("Номер автомобиля: " + entry.getKey() + "; кол-во поездок: " + entry.getValue());
            }*/

            //task 5
            /*Collections.sort(trips);
            for (Trip t : trips) {
                System.out.println(t);
            }*/

            //task 6
            /*Set<String> diffNames = trips.stream()
                    .map(c -> c.getName())
                    .collect(Collectors.toSet());

            System.out.println(diffNames.size());*/

            //task 7
            /*Scanner sc = new Scanner(System.in);
            String address = sc.nextLine();
            long countCl = trips.stream()
                    .filter(a -> a.getFrom().contains(address))
                    .map(t -> t.getName())
                    .distinct()
                    .count();
            System.out.println(countCl);*/

            //task 8

            /*Set<String> addressTo = trips.stream()
                    .map(a -> a.getTo())
                    .collect(Collectors.toSet());

            Set<String> addressFrom = trips.stream()
                    .map(a -> a.getFrom())
                    .collect(Collectors.toSet());

            addressFrom.addAll(addressTo);
            System.out.println(addressFrom.size());*/

            //task 9
            /*Set<String> diffAutoCount = trips.stream()
                    .map(a -> a.getAuto())
                    .collect(Collectors.toSet());

            System.out.println(diffAutoCount.size());*/

            //task 10
            /*Set<String> diffDriverCount = trips.stream()
                    .map(a -> a.getDriverNumber())
                    .collect(Collectors.toSet());

            System.out.println(diffDriverCount.size());*/

        } catch (IOException ex) {
            System.out.println("Ошибка чтения файла: " + ex.getMessage());
        }
    }
}
