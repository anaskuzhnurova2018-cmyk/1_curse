import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class List501implement<T> implements List501<T> {
    private int size = 0;
    private Object[] list = new Object[10];

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isExist(T o) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(T o) {
        if (size == list.length) {
            Object[] newList = new Object[list.length * 2];
            System.arraycopy(list, 0, newList, 0, list.length);
            list = newList;
        }
        list[size++] = o;
    }

    @Override
    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        T element = (T) list[index];
        for (int i = 0; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[--size] = null;
        return element;
    }

    @Override
    public void clean() {
        for (int i = 0; i < size; i++) {
            list[i] = null;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) list[index];
    }

    @Override
    public Object[] getAll() {
        Object[] result = new Object[size];
        System.arraycopy(list, 0, result, 0, size);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append("; ");
        }
        return sb.toString();
    }

    @Override
    public List501<T> filter(Predicate<T> predicate) {
        List501<T> result = new List501implement<>();
        for (int i = 0; i < size; i++) {
            if (predicate.test((T)list[i])) {
                result.add((T)list[i]);
            }
        }
        return result;
    }

    @Override
    public <R> List<R> collect(Function<T,R> function) {
        List<R> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(function.apply((T)list[i]));
        }
        return result;
    }

    @Override
    public List<T> sort(Comparator<T> comparator) {
        Object[] m = this.getAll();
        Arrays.sort((T[])m,comparator);
        List<T> result = new ArrayList<>();
        for (Object o : m) {
            result.add((T)o);
        }
        return result;
    }
}
