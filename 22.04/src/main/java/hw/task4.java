package hw;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class task4 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("C:\\ITISproga\\season26\\22.04\\src\\main\\java\\hw\\input.txt");
        FileWriter fw = new FileWriter("task4_res.txt");
        int c;

        while ((c = fr.read()) != -1) {
            fw.write((char) c);
        }

        fr.close();
        fw.close();
    }
}

/*
4) скопировать файл посимвольно, использовать FileReader и FileWriter
* */