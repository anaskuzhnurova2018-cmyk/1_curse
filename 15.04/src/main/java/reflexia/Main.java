package reflexia;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Group group = new Group();
        group.setName("11-501");

        Student student = new Student("Первый", 12, group);

        //---------------------------------------------------------------------
        // определим класс объекта
        System.out.println(student.getClass().getName());

        //определение публичных членов класса
        Field[] fields = student.getClass().getFields();

        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("//--------------------------------------------------------");
        //определение всех членов класса
        Field[] allFields = student.getClass().getDeclaredFields();
        for (Field field : allFields) {
            System.out.println(field.getClass().getName() + " : " + field.getName());
        }

        System.out.println("//--------------------------------------------------------");
        //определяем публичные методы
        Method[] methods = student.getClass().getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("//--------------------------------------------------------");
        //определяем публичные методы
        Method[] allMethods = student.getClass().getDeclaredMethods();
        for (Method method : allMethods) {
            System.out.println(method);
        }

        System.out.println("//--------------------------------------------------------");
        //получить значение членов нашего класса
        //нехороший способ
        for (Field field : allFields) {
            field.setAccessible(true);
            System.out.println(field.get(student));
        }

        //хороший способ
        for (Field field : allFields) {
            //контструируем имя геттера
            String getterName = "get"
                    + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);

            Method getter = student.getClass().getMethod(getterName);
            if (getter != null) {
                //вызываем метод
                Object value = getter.invoke(student);
                System.out.println(getter);
            }
        }
    }
}
