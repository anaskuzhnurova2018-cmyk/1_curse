package annotation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) throws ParseException {

        Date date = new Date();

        System.out.println(date.getTime()); //столько миллисекунд прошло с 1 января 1970 года

        // Задание конкретной даты
        // +1 сутки
        Date date2 = new Date(date.getTime() + 1000*60*60*24);
        System.out.println(date2);

        Date date3 = new SimpleDateFormat("dd.MM.yyyy").parse("21.05.2026");
        System.out.println(date3);

        //вывести красиво дату и время

        System.out.println(new SimpleDateFormat("dd.MM.yy hh:mm:ss").format(date));
        System.out.println(new SimpleDateFormat("dd.MM.yy hh:mm").format(date));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
    }
}
