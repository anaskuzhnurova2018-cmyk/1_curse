package Input_Output_stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainSaveToFile {
    public static void main(String[] args) {
        try (OutputStream os = new FileOutputStream("тест.txt")) {
            String str1 = "Тестовая строка 1\n";
            os.write(str1.getBytes("cp1251"));
            String str2 = "Тестовая строка 2\n";
            os.write(str2.getBytes());
            String str3 = "Тестовая строка 3\n";
            os.write(str3.getBytes());
            String str4 = "Тестовая строка 4\n";
            os.write(str4.getBytes());
            //сбросим буферы записи на устройства
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
