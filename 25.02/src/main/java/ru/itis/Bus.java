package ru.itis;

public class Bus extends Transport{
    public Bus(String number, String routeNumber, String workTimeBegin, String workTimeEnd) {
        super(TransportType.bus, number, routeNumber, workTimeBegin, workTimeBegin);
    }

    @Override
    public String toString() {
        return "Автобус номер " + getNumber() + ", маршрут " + getRouteNumber()
                + ", начало работы " + getWorkTimeBegin() + ", завершение работы " + getWorkTimeEnd();
    }
}
