import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface List501<T> {
    int size();
    boolean isEmpty();
    boolean isExist(T o);
    void add(T o);
    T delete(int index);
    void clean();
    T get(int index);
    Object[] getAll();
    List501<T> filter(Predicate<T> predicate);
    <R> List<R> collect(Function<T,R> function);
    List<T> sort(Comparator<T> comparator);
}
