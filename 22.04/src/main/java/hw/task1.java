package hw;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class task1 {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(bs, Charset.forName("cp1251"));
        PrintWriter pw = new PrintWriter(osw);
        pw.println("Добрый вечер!");
        pw.println("Добрый вечер!");
        pw.println("Добрый вечер!");
        pw.close();

        byte[] bytes = bs.toByteArray();

        FileOutputStream fos = new FileOutputStream("task1_res.txt");
        fos.write(bytes);
        fos.close();
    }
}

/*
1) используя PrintWriter и ByteArrayOutputStream сформировать массив байт,
содержащий текст из нескольких строчек;
получить массив и записать его в файл в кодировке cp1251
* */