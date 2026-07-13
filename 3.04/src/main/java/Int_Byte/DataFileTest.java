package Int_Byte;

/*
релизовать работу с бинарным файлом, хранящим функцию в следующей форме:
храним информацию о каком-то человеке:
Person
    name (ФИО) String
    birthday: String(yyyy-MM-dd)
    gender: byte {0 - M, 1 - F}
все это нужно сохранить в файл
проблема возникнет, когда захотим это прочитать, так как ФИО может содержать разное кол-во букв
варианты: можно класть сначало пол, др, а потом имя (детерминированный)
более сложный, но универсальный:
протокол и формат
ФОРМАТ: (описываем параметр в загаловке, а потом уже описываем тело файла)
HEADER (строго детерминированный, описываем как файл устроен)
DATA

lengthName(byte - 255 символов)
name (lengthName)
birthday: y(2 byte)M(1)d(1)
gender: (1)
* */

import java.io.*;

public class DataFileTest {
    private static final String FILE_NAME = "person.dat";

    public void saveToFile(Person p) throws IOException {
        try (DataOutputStream os = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            //пишем в UTF-кодировке (UTF-8)
            os.writeUTF(p.getName());
            //пишем год рождения
            os.writeShort(Short.parseShort(p.getBirthDate().substring(0, 4)));
            //пишем месяц рождения
            os.writeByte(Byte.parseByte(p.getBirthDate().substring(5, 7)));
            //пишем день рождения
            os.writeByte(Byte.parseByte(p.getBirthDate().substring(8, 10)));
            //пишем пол
            os.write(p.getGender());
            os.flush();
        }
    }

    public Person readFromFile() throws IOException{
        try (DataInputStream is = new DataInputStream(new FileInputStream("C:\\ITISproga\\season26\\person.dat"))) {
            Person person = new Person();
            person.setName(is.readUTF());
            int year = is.readShort();
            int month = is.readByte();
            int day = is.readByte();
            person.setBirthDate(year, month, day);
            person.setGender(is.readByte());
            return person;
        }
    }

}
