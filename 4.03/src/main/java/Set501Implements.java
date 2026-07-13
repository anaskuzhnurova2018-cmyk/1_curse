import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Set501Implements <T> implements Set501<T>{
    private static final int COUNT_BLOCKS = 16;
    private Object[] set = new Object[COUNT_BLOCKS];
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
        for (int i = 0; i < size; i++) {
            if (set[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public void add(T o) {
        if (size == set.length) {
            Object[] newSet = new Object[set.length * 2];
//            for (int i = 0; i < set.length; i++) {
//                newSet[i] = set[i];
//            }
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
            throw new NoSuchElementException();
        }
        for (int i = pos; i < size - 1; i++) {
            set[i] = set[i + 1];
        }
        set[size - 1] = null;
        size--;
    }

    @Override
    public void clean() {
        for (int i = 0; i < size; i++) {
            set[i] = null;
        }
    }

    @Override
    public Object[] getAll() {
        Object[] result = new Object[size];
        System.arraycopy(set, 0, result, 0, size);
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += set[i].toString() + "; ";
        }
        return result;
    }

    @Override
    public Set501<T> filter(Predicate<T> predicate ) { //фильтрует элементы множества на основе заданного условия
        //Predicate - встроенный функциональный интерфейс, который представляет собой условие (логическое выражение)
        //имеет только один метод: boolean test(T t)
        Set501<T> result = new Set501Implements<>(); //создаем новое пустое множество, в которое будем складывать только те элементы, которые прошли проверку
        for (int i = 0; i < size; i++) {
            if(predicate.test((T)set[i])) { //приводим элемент set[i] к типу T; проверяем условие
                result.add((T) set[i]);
            }
        }
        return result;
    }

    @Override
    public <R> List<R> collect(Function<T,R> function) { //метод преобразует каждый элемент мн-ва по заданному правилу и возвращает список рез-тов преобразование
        //<R> - параметр типа возвращаемого значения, это обобщенный тип(generic)
        //у Function только один метод: apply (принимает объект типа T, возвращает объект типа R)
        List<R> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(function.apply((T)set[i]));
        }
        return result;
    }

    @Override
    public List<T> sort(Comparator<T> comparator) {
        Object[] m = this.getAll();
        Arrays.sort((T[])m,comparator);
        List<T> result = new ArrayList<>();
        for (Object o : m) {
            result.add((T) o);
        }
        return result;
    }


}
