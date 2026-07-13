package KR1_VAR2;

public class Trip implements Comparable<Trip>{
    private String number;
    private String name;
    private String auto;
    private String driverNumber;
    private String driverName;
    private String from;
    private String to;
    private TripDate departureTime;
    private TripDate arrivalTime;

    public Trip(String str) {
        String[] parts = str.split(";");
        this.number = parts[0].trim();
        this.name = parts[1].trim();
        this.auto = parts[2].trim();
        this.driverNumber = parts[3].trim();
        this.driverName = parts[4].trim();
        this.from = parts[5].trim();
        this.to = parts[6].trim();
        this.departureTime = new TripDate(parts[7].trim());
        this.arrivalTime = new TripDate(parts[8].trim());
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public TripDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(TripDate departureTime) {
        this.departureTime = departureTime;
    }

    public TripDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(TripDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(Trip o) {
        return this.getDepartureTime().compareTo(o.getDepartureTime());
    }

    @Override
    public String toString() {
        return number + " | " + name + " | " + auto + " | " + driverNumber + " | " + driverName + " | " + from + " | " + to + " | " + departureTime + " | " + arrivalTime;
    }
}
