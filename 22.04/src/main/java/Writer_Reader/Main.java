package Writer_Reader;

import java.io.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("test_writer.txt"); //Charset.forName("utf-8"), true);
        writer.write('A');
        writer.write('Б'); //void - ничего не возвращает

        writer.append('В').append('Г').append('Д'); //сам себя возвращает, дает воз-ть делать такую цепочку, удобно там, где возникает функциональный стиль программирования

        writer.flush();
        writer.close();

        try (FileReader reader =
                     new FileReader("test_writer.txt")) {

            int c;
            while ((c = reader.read()) != -1) {
                System.out.println((char) c);
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\ITISproga\\season26\\transport.csv"))) {
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        }

        StringWriter stringWriter = new StringWriter();

        stringWriter.append('A').append('B').append('C');
        System.out.println(stringWriter.toString());

        try (PrintWriter pw = new PrintWriter(new FileOutputStream("test_pw.txt"))) {
            pw.println("Добрый день");
            pw.println("Добрый вечер");
            pw.println("Доброй ночи");
        }
    }
}
