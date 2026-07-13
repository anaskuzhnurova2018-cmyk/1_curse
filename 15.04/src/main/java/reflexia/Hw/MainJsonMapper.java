package reflexia.Hw;

// jsonMapper вызвать и распечатать какой-нибудь объект

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainJsonMapper {
    public static void main(String[] args) throws FileNotFoundException {
        /*Human human1 = new Human("Anastasia", "Kuzhnurova", 18, 155, 45);
        JsonMapper jsonMapper = new JsonMapper();
        System.out.println(jsonMapper.write(human1));*/

        try (BufferedReader bf = new BufferedReader(new FileReader("C:\\ITISproga\\season26\\15.04\\src\\main\\java\\reflexia\\Hw\\input.txt"))) {
            String line = bf.readLine();
            String[] parts = line.split(",");

            Human human = new Human(
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[4])
            );

            JsonMapper jsonMapper = new JsonMapper();
            String jsonResult = jsonMapper.write(human);

            System.out.println(jsonResult);
            Files.writeString(Path.of("output.json"), jsonResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
