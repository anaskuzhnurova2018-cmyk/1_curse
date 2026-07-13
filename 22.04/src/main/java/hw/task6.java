package hw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class task6 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\ITISproga\\season26\\22.04\\src\\main\\java\\hw\\input.txt");
        FileOutputStream fos = new FileOutputStream("task6_res.txt");

        int c;
        while((c = fis.read()) != -1) {
            fos.write(c);
        }

        fis.close();
        fos.close();

    }
}

/*
6) скопировать файл побайтно,
используя FileInputStream  и FileOutputStream

* */