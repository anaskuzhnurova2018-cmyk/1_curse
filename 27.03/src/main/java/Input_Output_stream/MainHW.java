package Input_Output_stream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MainHW {
    public static void main(String[] args) {
        try(OutputStream file = new FileOutputStream("file.json")) {
            String str = "{\n" +
                    " \"anastasia\": 18, \n" +
                    " \"diana\": 19, \n" +
                    " \"group\": 501, \n" +
                    " \"birthday\": 24, \n" +
                    " \"Mari El\": 12 \n" +
                    "}\n";
            file.write(str.getBytes());

            readAndPrintJson();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAndPrintJson() {
        try (InputStream input = new FileInputStream("C:\\ITISproga\\season26\\file.json")) {
            byte[] buffer = new byte[4096];
            int count = 0;
            int r;

            while((r = input.read()) != -1) {
                buffer[count++] = (byte) r;
            }

            String jsonRow = new String(buffer, 0, count, "UTF-8"); //создание строки из части массива байтов
            String jsonClean = jsonRow
                    .replace("{", "")
                    .replace("}", "")
                    .replace("\n", "")
                    .trim(); //удаляет пробелы в начале и конце строки

            String[] pairs = jsonClean.split(",");

            Map<String, Integer> data = new HashMap<>();
            for (String pair : pairs) {
                pair = pair.trim();
                if (pair.isEmpty()) continue;

                int colonelIndex = pair.indexOf(":");
                String key = pair.substring(0, colonelIndex)
                        .trim()
                        .replace("\"", "");
                //substring - подстрока

                String valueStr = pair.substring(colonelIndex + 1)
                        .trim();
                int value = Integer.parseInt(valueStr);
                data.put(key, value);
            }

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
1.
составить json объект:
не менее 3 полей, 1 должно быть числовое
записать в файл
2.
прочитать из файла json объект
распарсить его
вывести в формате ключ : значение
* */