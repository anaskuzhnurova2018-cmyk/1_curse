package Input_Output_stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main2 {
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("C:\\ITISproga\\season26\\13.03\\booking.json")) {
            byte[] buffer = new byte[4096];
            int count = 0;
            int r;

            while ((r = is.read()) != -1) {
                if ((char) r == '\n') {
                    String str = new String(buffer, 0, count, "UTF-8");
                    System.out.println(str);
                    count = 0;
                } else {
                    buffer[count++] = (byte) r;
                }
            }

            System.out.println();;
        } catch (FileNotFoundException e) {
            System.out.println("Не смогли найти входной файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
