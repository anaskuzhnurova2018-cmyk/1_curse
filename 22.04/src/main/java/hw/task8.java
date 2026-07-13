package hw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class task8 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\ITISproga\\season26\\22.04\\src\\main\\java\\hw\\input.txt");
        FileOutputStream fos = new FileOutputStream("task8_res.txt");

        FileChannel sourceChannel = fis.getChannel();
        FileChannel targetChannel = fos.getChannel();

        sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);

        fis.close();
        fos.close();
    }
}

/*
8) скопировать файл,
используя FileInputStream  и FileOutputStream и метод transferTo

* */