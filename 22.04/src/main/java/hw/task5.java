package hw;

import java.io.*;

public class task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\ITISproga\\season26\\22.04\\src\\main\\java\\hw\\input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("task5_res.txt"));

        String str;

        while ((str = br.readLine()) != null) {
            bw.write(str);
            bw.newLine();
        }

        br.close();
        bw.close();

    }
}

/*
5) скопировать файл построчно,
использовать BufferedReader и BufferedWriter
* */