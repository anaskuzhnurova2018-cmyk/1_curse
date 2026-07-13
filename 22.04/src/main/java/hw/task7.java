package hw;

import java.awt.image.BufferedImage;
import java.io.*;

public class task7 {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bif = new BufferedInputStream(new FileInputStream("C:\\ITISproga\\season26\\22.04\\src\\main\\java\\hw\\input.txt"));
        BufferedOutputStream bof=  new BufferedOutputStream(new FileOutputStream("task7_res.txt"));

        int c;
        while((c = bif.read()) != -1) {
            bof.write(c);
        }

        bif.close();
        bof.close();
    }
}

/*
7) скопировать файл,
используя BufferedInputStream  и BufferedOutputStream

* */