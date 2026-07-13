package ru.itis;

public abstract class Transport {
    private TransportType type;
    private String number;
    private String routeNumber;
    private String workTimeBegin;
    private String workTimeEnd;

    public Transport() {}

    public Transport(TransportType type, String number, String routeNumber, String workTimeBegin, String workTimeEnd) {
        this.type = type;
        this.number = number;
        this.routeNumber = routeNumber;
        this.workTimeBegin = workTimeBegin;
        this.workTimeEnd = workTimeEnd;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getWorkTimeBegin() {
        return workTimeBegin;
    }

    public void setWorkTimeBegin(String workTimeBegin) {
        this.workTimeBegin = workTimeBegin;
    }

    public String getWorkTimeEnd() {
        return workTimeEnd;
    }

    public void setWorkTimeEnd(String workTimeEnd) {
        this.workTimeEnd = workTimeEnd;
    }
}
