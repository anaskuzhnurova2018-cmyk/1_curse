package ru.itis;

public class Tram extends Transport{
    public Tram(String number, String routeNumber, String workTimeBegin, String workTimeEnd) {
        super(TransportType.tram, number, routeNumber, workTimeBegin, workTimeBegin);
    }

    @Override
    public String toString() {
        return "Трамвай номер " + getNumber() + ", маршрут " + getRouteNumber()
                + ", начало работы " + getWorkTimeBegin() + ", завершение работы " + getWorkTimeEnd();
    }
}
