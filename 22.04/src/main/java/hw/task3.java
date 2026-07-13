package hw;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class task3 {
    public static void main(String[] args) throws IOException {
        String str = "good evening";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        ByteArrayInputStream baos = new ByteArrayInputStream(bytes);
        InputStreamReader isr = new InputStreamReader(baos);

        int str1;

        while ((str1 = isr.read()) != -1) {
            System.out.println((char) str1);
        }
    }
}

/*
3) строку преобразовать  в массив байт,
на базе этого массива создать ByteArrayInputStream,
на базе этого объекта сформировать InputStreamReader,
в цикле считать и распечатать все символы

* */