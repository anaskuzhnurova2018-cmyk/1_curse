package hw;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date birthdate = sdf.parse("24.04.2007");
        User user = new User("Anastasia", "null", birthdate, "school");
        JsonMapper jsonMapper = new JsonMapper();
        String jsonResult = jsonMapper.write(user);
        System.out.println(jsonResult);
        Files.writeString(Path.of("output.json"), jsonResult);
    }
}
