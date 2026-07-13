package lab2_7.generic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class Set501Implement<T> implements Set501Gen<T> {
    private Object[] set = new Object[3];
    //T[] r = new T[];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean checkEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T o) {
        return false;
    }

    @Override
    public void add(T o) {

        if (size == set.length) {
            Object[] newSet = new Object[set.length * 2];
/*
            for (int i = 0; i < set.length; i++) {
                newSet[i] = set[i];
            }
*/
            System.arraycopy(set, 0, newSet, 0, set.length);
            set = newSet;
        }
        set[size++] = o;
    }

    @Override
    public void delete(T o) {

        int pos = -1;
        for (int i = 0; i < set.length; i++) {
            if (set[i].equals(o)) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = pos; i < size - 1; i++) {
            set[i] = set[i+1];
        }
        set[size - 1] = null;
        size--;
    }

    @Override
    public void clean() {
        for (int i = 0; i < size; ++i) {
            set[i] = null;
        }
        size = 0;
    }

    /*
      T[] newVal = (T[])Array.newInstance(c.getClass().componentType(),size);
     */
    @Override
    public Object[] getAll() {
        Object[] result = new Object[size];
        System.arraycopy(set, 0, result, 0, size);
        return result;
    }

    /*
      T[] newVal = (T[])Array.newInstance(componentType,size);
      Array.newInstance(Person.class(), 10)
     */
    public T[] getAll(Class<T> clazz) {
        T[] result = (T[]) Array.newInstance(clazz,size); //Array.newInstance() создает массив заданного типа и размера, class - тип элементов массива
        System.arraycopy(set, 0, result, 0, size);
        return result;
    }


    @Override
    public String toString() {
        return "Set501Implement{" +
                "set=" + Arrays.toString(set) +
                ", size=" + size +
                '}';
    }

    @Override
    public Set501Gen<T> filter(Predicate<T> predicate) {
        Set501Gen<T> result = new Set501Implement<>();

        for (int i = 0; i < size; i++) {
            if (predicate.test((T) set[i])) {
                result.add((T) set[i]);
            }
        }
        return result;
    }

    public <R> List<R> collect(Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(function.apply((T)set[i]));
        }
        return result;
    }

    public List<T> sort(Comparator<T> comparator) {
        Object[] m = this.getAll();
        Arrays.sort((T[]) m, comparator);
        List<T> result = new ArrayList<>();
        for (Object o: m) {
            result.add((T) o);
        }
        return result;
    }
}
