package annotation;

//указать время действия, где будет доступна
//определяем, кому она предназначена (методу, классу и тд)

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) //на членов нашего класса
public @interface DefaultValue {
    String value() default "1";
}
