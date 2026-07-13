package ru.itis;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TransportService transportService = new TransportService();
        PrintService printService = new PrintService();

        System.out.println("Рабочая директория:" + System.getProperty("user.dir"));

        List<Transport> transport = transportService.readAll();

        printService.printNumberOfRoutes(transport);

        printService.printNumberofTypeTransport(transport);

        printService.printParkingNumbersAndBeginTime(transport);

        printService.printRouteNumberAndTime(transport);

        printService.printRouteNumberAndTransport(transport);
    }
}
