package Lesson11;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task7 extends JFrame {
    public static void main(String[] args) {
        //создаем окно
        Task7 app = new Task7();
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setSize(800, 600);
        app.setVisible(true);

        //поступление точек, генерируется случайно
        Random rand = new Random();

        //добавляем точки с паузой, чтобы видеть процесс
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    int x = rand.nextInt(30); //координаты от 0 до 30
                    int y = rand.nextInt(20);

                    Point p = new Point(x, y);
                    app.addPoint(p);
                    Thread.sleep(1000); //пауза 1 секунда между точками
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
    List<Point> allPoints = new ArrayList<>();
    List<Point> hull = new ArrayList<>(); //текущая оболочка

    public boolean isInside(List<Point> hull, Point p) {
        if (hull.size() < 3) return false; //пока нет треугольника, точки всегда снаружи

        for (int i = 0; i < hull.size(); i++) {
            Point a = hull.get(i);
            Point b = hull.get((i + 1) % hull.size()); //след вершина (циклично)

            if (Point.cross(a, b, p) < 0) {
                return false;
            }
        }
        return true; //храним оболочку против часовой стрелки
    }

    public void addPoint(Point p) {
        allPoints.add(p);

        if (hull.size() < 3) {
            hull.add((p));
            repaint();
            return;
        }

        if (isInside(hull, p)) {
            repaint();
            return;
        }
        updateHull(p);
        repaint();
    }

    public void updateHull(Point newPoint) {
        hull.add(newPoint);
        recalculateHull();
    }

    public void recalculateHull() {
        if (allPoints.isEmpty()) return;

        List<Point> newHull = new ArrayList<>();
        Point startPoint = allPoints.get(0); //нашли самую левую нижнюю точку
        for (Point p : allPoints) {
            if (p.getX() < startPoint.getX() || (p.getX() == startPoint.getX() && p.getY() < startPoint.getY())) {
                startPoint = p;
            }
        }

        Point currentPoint = startPoint;
        newHull.add(currentPoint);

        while (true) {
            Point nextPoint = allPoints.get(0); //кандидат просто
            for (Point candidate : allPoints) {
                if (candidate.equals(currentPoint)) continue;

                long cross = Point.cross(currentPoint, nextPoint, candidate); //если candidate левее, чем nextPoint (>0) или если они на одной линии, но candidate дальше
                if (cross > 0 || (cross == 0 && dist(currentPoint, candidate) > dist(currentPoint, nextPoint))) {
                    nextPoint = candidate;
                }
            }
            currentPoint = nextPoint;

            if (currentPoint.equals(startPoint)) break; //вернулись в начало
            newHull.add(currentPoint);

            if (newHull.size() > allPoints.size()) break; //если все точки на одной линии, чтобы не было бесконечного цикла
        }
        this.hull = newHull;
    }

    public long dist(Point a, Point b) { //вспомогательный метод расстояния
        long dx = a.getX() - b.getX();
        long dy = a.getY() - b.getY();
        return dx*dx + dy*dy;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int scale = 20;
        int offsetX = 50;
        int offsetY = 50;

        g.setColor(Color.GRAY);
        for (Point p : allPoints) { //рисуем точки
            p.draw(g, scale, offsetX, offsetY);
        }

        //рисуем оболочку
        if (hull.size() > 1) {
            g.setColor(Color.RED);
            for (int i = 0; i < hull.size(); i++) {
                Point p1 = hull.get(i);
                Point p2 = hull.get((i + 1) % hull.size()); //замыкаем на первую точку

                int x1 = p1.getX() * scale + offsetX;
                int y1 = p1.getY() * scale + offsetY;
                int x2 = p2.getX() * scale + offsetX;
                int y2 = p2.getY() * scale + offsetY;

                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
}

/*
7. Задача об отыскании выпуклой оболочки множества Q в режиме
«on-line» состоит в следующем:
нам поступает n точек одна за другой.
Построить алгоритм,
который для любого момента времени строит выпуклую облочку для полученных к этому времени точек.
Время О(n^2)
Также решение отобразить графически
* */