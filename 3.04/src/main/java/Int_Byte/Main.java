package Int_Byte;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Person p = new Person();
        p.setName("Абрамский М. М.");
        p.setBirthDate("1998-08-30");
        p.setGender((byte) 0);

        DataFileTest converter = new DataFileTest();
        converter.saveToFile(p);
        System.out.println(converter.readFromFile());
    }
}
