package ru.itis;

public class Trolleybus extends Transport{
    public Trolleybus(String number, String routeNumber, String workTimeBegin, String workTimeEnd) {
        super(TransportType.trolleybus, number, routeNumber, workTimeBegin, workTimeEnd);
    }

    @Override
    public String toString() {
        return "Троллейбус номер " + getNumber() + ", маршрут " + getRouteNumber()
                + ", начало работы " + getWorkTimeBegin() + ", завершение работы " + getWorkTimeEnd();
    }
}
