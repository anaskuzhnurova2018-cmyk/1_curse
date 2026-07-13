package lab2_7.generic;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public interface Set501Gen<T> {
    /*методы:
размер
проверка на пустоту
проверка наличия элемента
добавление элемента
удаление элемента (по значению)
очистка структуры*/
    int size();
    boolean checkEmpty();
    boolean contains(T o);
    void add(T o);
    void delete(T o);
    void clean();
    Object[] getAll();
    Set501Gen<T> filter(Predicate<T> predicate);
    <R> List<R> collect(Function<T, R> function);
    List<T> sort(Comparator<T> comparator);

}
