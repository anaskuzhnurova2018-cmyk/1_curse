package Lesson11;

import javax.swing.*;
import java.awt.*;

public class Example extends JFrame {
    public static void main(String[] args) {

        Example printLineForEx11 = new Example();
    }

    public Example() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    public void paint(Graphics g) {
//        x1, y1 - x2,y2
        g.drawLine(10, 10 + 50, 200, 200);
        //рисуем линию от точки (10, 60) до точки (200, 200)
    }
}
