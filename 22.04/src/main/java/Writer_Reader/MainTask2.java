package Writer_Reader;

import java.io.*;

public class MainTask2 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("C:\\ITISproga\\season26\\22.04\\src\\main\\java\\Writer_Reader\\task2.txt"));
             FileWriter w = new FileWriter("task2_result.txt")) {
            int c;
            int wordLen = 0;
            boolean startWord = true;
            boolean isMarked = false;
            while (( c = r.read()) != -1) {

                if (startWord && (c == 'А' || c == 'а')) {
                    r.mark(64); // фиксируем текущую позицию
                    isMarked = true;
                    wordLen = 1;
                }

                if (!startWord &&(c == 'н' || c == 'Н')) {
                    r.reset();

                    char[] upperWord = new char[wordLen];
                    r.read(upperWord);

                    String s = new String(upperWord).toUpperCase();
                    w.write(s);

                    wordLen = 0;
                    isMarked = false;
                    continue;
                }

                if (c == ' ') {
                    startWord = true;
                } else {
                    startWord = false;
                }

                if (!isMarked) {
                    w.write(c);
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

/*
2) прочитать текст из файла с помощью FileReader , если слово начинается с буквы A и заканчивается буквой H,
то перевести его в верхний регистр
* */