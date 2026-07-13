package annotation;

import java.lang.classfile.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/*
создать экземпляр класса Student
для полей отмеченных аннотацией DefaultValue задать значение из атрибута анотации
* */

public class MainAnnotationReader {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //задача: создать объект и проинициализировать его
        Class clazz = Student.class;

        //1) создать экземпляр нашего класса
        Constructor constructor = clazz.getConstructor();

        Student student = (Student) constructor.newInstance();
        System.out.println(student);

        //просканируем наши поля и над теми полями, над которыми стоит конструкция DefaultValue, проставить значение
        //считыаем список всех членов
        Field[] fields = clazz.getDeclaredFields(); //будем считывать все поля

        //нам нужно вытащить атрибут, который описывает значение по умолчанию
        for (Field field: fields) {
            if (field.isAnnotationPresent(DefaultValue.class)) {
                DefaultValue annotation = field.getAnnotation(DefaultValue.class);
                String value = ((DefaultValue)annotation).value();
                field.setAccessible(true);
                field.set(student, value);
            }
        }

        System.out.println(student);

    }
}
