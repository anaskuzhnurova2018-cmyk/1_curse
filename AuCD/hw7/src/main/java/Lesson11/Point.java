package Lesson11;

import java.awt.*;
import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }


    //нужно уметь проверять, есть ли точка в множестве
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    //метод для векторного произведения, который проверяет, где находится
    //точка: внутри или снаружи текущей оболочки
    //cross = (x2-x1)*(y3-y1) - (y2-y1)*(x3-x1)
    //>0 (точка C слева от вектора АВ (поворот против часовой)
    // <0 точка С справа от вектора АВ (поворот по часовой)
     // = 0 точки на одной прямой

    public static long cross (Point a, Point b, Point c) {
        return (long)(b.x - a.x) * (c.y - a.y) - (long) (b.y - a.y) * (c.x - a.x);
    }

    public void draw(Graphics g, int scale, int offsetX, int offsetY) {
        int screenX = x * scale + offsetX;
        int screenY = y * scale + offsetY;
        g.fillOval(screenX - 3, screenY - 3, 6, 6); //кружок 6*6 пикселей
    }
}
