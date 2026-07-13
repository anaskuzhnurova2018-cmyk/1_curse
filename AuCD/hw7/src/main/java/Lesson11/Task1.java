package Lesson11;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

/*
сортируем точки по координате X (слева направо),
а при равенстве X - по Y, и соединить их в этом порядке -
получится искомая ломаная
* */


public class Task1 extends JFrame{
    private int[][] points; //массив точек
    private static final int SCALE = 30; // 1 единица координат = 30 пикселей на экране

    public Task1(int[][] points) {
        this.points = points;
        setDefaultCloseOperation(EXIT_ON_CLOSE); // при нажатии на крестик программа завершится
        setSize(500, 500); // размер окна в пикселях
        setVisible(true); //показать окно

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //очищает фон и рисует рамки окна

        //получаем область для рисования внутри окна
        Insets insets = getInsets(); //нужен, чтобы линии не рисовались поверх окна и рамок (возвращает отступы: сколько пикселей занимает рамка)
        int offsetX = insets.left; //сдвиг, чтобы рисовать внутри окна, а не поверх рамок
        int offsetY = insets.top;

        for (int i = 0; i < points.length - 1; i++) { //рисуем отрезки, на один меньше, чем кол-во точек
            drawLine(g, points[i], points[i + 1], offsetX, offsetY);
        }
    }

    private void drawLine(Graphics g, int[] p1, int[] p2, int offX, int offY) {
        int x1 = p1[0] * SCALE + offX; //превращаем координаты в пискели
        int y1 = p1[1] * SCALE + offY;
        int x2 = p2[0] * SCALE + offX;
        int y2 = p2[1] * SCALE + offY;
        g.drawLine(x1, y1, x2, y2); //рисуем отрезок между двумя точками
    }

    public static void main(String[] args) {

        int[][] points = {
                {1, 6},
                {4, 1},
                {3, 10},
                {13, 0},
                {5, 1},
                {9, 2},
                {6, 7},
                {4, 8},
                {9, 9},
                {5, 0}
        };


        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                if (p1[0] != p2[0]) { //сначала X сравниваем
                    return Integer.compare(p1[0], p2[0]);
                }
                return Integer.compare(p1[1], p2[1]); //Y сравниваем, если X равны
            }
        });

        new Task1(points);
    }

}

/*
Занятие 11

1. Дано n точек на плоскости.
Указать (n − 1)-звенную несамопересекающуюся незамкнутую ломаную,
проходящую через все эти точки.
(Соседним отрезкам ломаной разрешается лежать на одной прямой.)
Число действий порядка n log n.
Реализовать отображение
* */