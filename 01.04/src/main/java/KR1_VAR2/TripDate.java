package KR1_VAR2;

public class TripDate implements Comparable<TripDate>{
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public TripDate(String str) {
        String[] parts = str.split("[-: ]");
        this.year = Integer.parseInt(parts[0]);
        this.month = Integer.parseInt(parts[1]);
        this.day = Integer.parseInt(parts[2]);
        this.hour = Integer.parseInt(parts[3]);
        this.minute = Integer.parseInt(parts[4]);
    }

    @Override
    public int compareTo(TripDate o) {
        if (this.year != o.year) {
            return this.year - o.year;
        }
        if (this.month != o.month) {
            return this.month - o.month;
        }
        if (this.day != o.day) {
            return this.day - o.day;
        }
        if (this.hour != o.hour) {
            return this.hour - o.hour;
        }
        return this.minute - o.minute;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day + " " + hour + ":" + minute;
    }
}
