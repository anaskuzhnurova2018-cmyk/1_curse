package hw;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonMapper {
    public String write(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (o == null) {
            return "null";
        }
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        boolean isField = true; //флаг, чтобы не ставить зпт перел первым полем
        StringBuilder result = new StringBuilder("{");

        if (clazz.equals(String.class)) {
            return "\"" + o + "\"";
        }

        if (clazz.equals(Integer.class)) {
            return String.valueOf(o);
        }

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(Transient.class)) {
                continue;
            }
            String fieldName = field.getName();
            String getterName = "get"
                    + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);

            Method getter = clazz.getMethod(getterName);
            Object value = getter.invoke(o);
            if (value == null) {
                if (field.isAnnotationPresent(OutputNullValue.class)) {
                    if (!isField) {
                        result.append(",");
                    }
                    result.append("\"")
                            .append(fieldName)
                            .append("\":")
                            .append("null");
                    isField = false;
                }
            } else if (value instanceof Date) {
                if (field.isAnnotationPresent(DateFormat.class)) {
                    DateFormat df = field.getAnnotation(DateFormat.class);
                    String pattern = df.value();
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    String formattedDate = sdf.format((Date) value);
                    if (!isField) {
                        result.append(",");
                    }
                    result.append("\"")
                            .append(fieldName)
                            .append("\": \"")
                            .append(formattedDate)
                            .append("\"");
                    isField = false;
                }
            } else { //для остальных значений рекурсивно вызываем write
                String jsonValue = write(value);

                if (!isField) result.append(",");
                result.append("\"")
                        .append(fieldName)
                        .append("\": ")
                        .append(jsonValue);
                isField = false;
            }
        }
        result.append("}");
        return result.toString();
    }
}
