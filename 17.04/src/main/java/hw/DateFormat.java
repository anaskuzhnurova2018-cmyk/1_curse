package hw;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //аннотация доступна во время выполнения
@Target(ElementType.FIELD) //можно использовать только на полях

public @interface DateFormat {
    String value() default "yyyy-MM-dd";
}
