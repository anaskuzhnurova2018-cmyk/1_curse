package project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Wastes implements Serializable {
    private Integer id;
    private int type;  // 1 - доход, -1 расход
    private String name;
    private Date date;
    private String comment;
    private float sum;

    public Wastes(Integer id, int type, String name, Date date, String comment, float sum) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.date = date;
        this.comment = comment;
        this.sum = sum;
    }

    public Wastes() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = date != null ? sdf.format(date) : "N/A";
        return "Wastes{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", date=" + formattedDate +
                ", comment='" + comment + '\'' +
                ", sum=" + sum + //
                '}';
    }
}
