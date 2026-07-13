import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Set501<T> {
    int size();
    boolean checkEmpty();
    boolean contains(T o);
    void add(T o);
    void delete(T o);
    void clean();
    Object[] getAll();
    Set501<T> filter(Predicate<T> predicate); //метод filter позволяет создать новый поток, содержащий только те элементы, которые соотвествуют заданному предикату; предикат - это функциональный интерфейс, который представляет условие или проверку входного значения, возвращая значение true / false
    <R> List<R> collect(Function<T,R> function); //позволяет получить данные не в виде потока, а в виде обычной коллекции, например, ArrayList или HashSet; T - тип элемента в потоке, R - тип промежуточных накапливаемых данных
    //можем использовать тип R
    List<T> sort(Comparator<T> comparator); //компаратор - это объект, который определяет порядок сравнения объектов
}
