package reflexia.Hw;

import java.lang.reflect.Field; //позволяет работать с полями класса
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonMapper {
    public String write(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 1 получить список всех полей, включая приватные +
        // 2 получить значание полей через рефлексию
        // 3 формируем json
        // значение строка - печатаем пару "имяПоля" : "значение"
        // значение число - печатаем пару "имяПоля" : значение
        // значение объект - печатаем пару "имяПоля" : write(value) - рекурсивно вызвать

        if (o == null) {
            return "null";
        }
        Class clazz = o.getClass();
        StringBuilder result = new StringBuilder("{");
        if (clazz.equals(String.class)) { //позволяет опредлить строка или нет
            return "\"" + o + "\"" ;
        }

        if (clazz.equals(Integer.class)) {
            return String.valueOf(o); //возвращаем строковое представление числа
        }

        Field[] fields = clazz.getDeclaredFields(); //получаем все поля класса,  включая private
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            String getterName = "get"
                    + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);

            Method getter = clazz.getMethod(getterName);
            Object value = getter.invoke(o); //вызываем геттер - получаем значение
            String formattedValue = write(value); //рекурсивно вызываем write для значения поля

            if (i > 0) {
                result.append(","); //если не первое поле, то добавляем зпт
            }

            result.append("\"");
            result.append(fieldName).append("\":");
            result.append(formattedValue);
        }
        result.append("}");
        return result.toString();
    }
}
