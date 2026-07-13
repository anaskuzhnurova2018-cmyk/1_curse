package Int_Byte.objectTo;

import java.io.*;

//чтобы сохранить какое-то состояние, используем этот класс (это типа слепок оперативной памяти)
//если нужно передать инфу, то можем провести сериализацию и передать через инте наш класс
//проблемы: сильно завязана на версии java

public class MainSerialize {
    public static void main(String[] args) {
        // 1. создаем объект
        Car car = new Car("KIA RIO", "седан", "12312313");
        // 2. сериализуем

        try (
            FileOutputStream fos = new FileOutputStream("car.obj");
            ObjectOutputStream os = new ObjectOutputStream(fos)) {

            os.writeObject(car);
            fos.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("car.obj"))) {
            Car car1 = (Car) is.readObject();
            System.out.println(car1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
