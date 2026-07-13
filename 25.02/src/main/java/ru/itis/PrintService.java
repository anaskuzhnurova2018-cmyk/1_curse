package ru.itis;

import java.util.*;

public class PrintService {
    public void printParkingNumbersAndBeginTime (List<? extends Transport> lst) {
        List<Transport> newLst = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            newLst.add(lst.get(i));
        }

        for (int j = 0; j < newLst.size(); j++) {
            for (int i = 0; i < newLst.size() - 1; i++) {
                if (newLst.get(i).getNumber().compareTo(newLst.get(i + 1).getNumber()) > 0) {
                    Transport tempor = newLst.get(i);
                    newLst.set(i, newLst.get(i + 1));
                    newLst.set(i + 1, tempor);
                }
            }
        }

        for (Transport t : newLst) {
            System.out.println(t.getNumber() + " отбывает в " + t.getWorkTimeBegin());
        }
    }

    public void printRouteNumberAndTime (List<? extends Transport> lst) {
        List<Transport> newLst = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            newLst.add(lst.get(i));
        }

        for (int j = 0; j < newLst.size(); j++) {
            for (int i = 0; i < newLst.size() - 1; i++) {
                if (newLst.get(i).getRouteNumber().compareTo(newLst.get(i + 1).getRouteNumber()) > 0) {
                    Transport temp = newLst.get(i);
                    newLst.set(i, newLst.get(i + 1));
                    newLst.set(i + 1, temp);
                }
            }
        }

        for (Transport t : newLst) {
            System.out.println("Маршрут номер: " + t.getRouteNumber() + " отбывает в " + t.getWorkTimeBegin() + "; прибывает в :" + t.getWorkTimeEnd());
        }
    }

    public void printNumberOfRoutes (List<? extends Transport> lst) {
        Set<String> uniqueRoutes = new HashSet<>();
        for (int i = 0; i < lst.size(); i++) {
            uniqueRoutes.add(lst.get(i).getRouteNumber());
        }
        System.out.println("Кол-во маршрутов: " + uniqueRoutes.size());
    }

    public void printNumberofTypeTransport (List<? extends Transport> lst) {
        Set<TransportType> uniqueTypes = new HashSet<>();
        for (int i = 0; i < lst.size(); i++) {
            uniqueTypes.add(lst.get(i).getType());
        }
        System.out.println("Кол-во типов трансопрта: " + uniqueTypes.size());
    }

    public void printRouteNumberAndTransport (List<? extends Transport> lst) {
        Map<String, Integer> map = new HashMap<>();
        for (Transport t : lst) {
            if (map.containsKey(t.getRouteNumber())) {
                map.put(t.getRouteNumber(), map.get(t.getRouteNumber()) + 1);
            } else {
                map.put(t.getRouteNumber(), 1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
