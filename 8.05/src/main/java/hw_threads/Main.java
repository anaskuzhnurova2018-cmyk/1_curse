package hw_threads;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        Element[] elements = new Element[16];

        Thread[] threads = new Thread[16];

        elements[0] = new Element(0, new Element[]{}, threads);
        elements[1] = new Element(1, new Element[]{elements[0]}, threads);
        elements[2] = new Element(2, new Element[]{elements[0]}, threads);
        elements[3] = new Element(3, new Element[]{elements[0]}, threads);
        elements[4] = new Element(4, new Element[]{elements[0], elements[1], elements[2]}, threads);
        elements[5] = new Element(5, new Element[]{elements[2]}, threads);
        elements[6] = new Element(6, new Element[]{elements[2]}, threads);
        elements[7] = new Element(7, new Element[]{elements[3]}, threads);
        elements[8] = new Element(8, new Element[]{elements[7]}, threads);
        elements[9] = new Element(9, new Element[]{elements[2]}, threads);
        elements[10] = new Element(10, new Element[]{elements[1]}, threads);
        elements[11] = new Element(11, new Element[]{elements[7]}, threads);
        elements[12] = new Element(12, new Element[]{elements[4], elements[5], elements[8]}, threads);
        elements[13] = new Element(13, new Element[]{elements[6], elements[9], elements[10], elements[12]}, threads);
        elements[14] = new Element(14, new Element[]{elements[11]}, threads);
        elements[15] = new Element(15, new Element[]{elements[13], elements[14]}, threads);

        for (int i = 0; i < 16; i++) {
            threads[i] = new Thread(elements[i]);
            threads[i].start();
        }

    }

}
