package hwLinkedList;

public class List501Implements<T> implements List501<T> {
    private static final int COUNT_BLOCKS = 3;
    private Object[] list = new Object[COUNT_BLOCKS];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T t) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(t)) return true;
        }
        return false;
    }

    @Override
    public void add(T t) {
        if (size == list.length) {
            Object[] newSet = new Object[list.length * 2];
            System.arraycopy(list, 0, newSet, 0, list.length);
            list = newSet;
        }
        list[size++] = t;
    }

    @Override
    public T delete(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T result = (T) list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[--size] = null;
        return result;
    }

    @Override
    public void clean() {
        for (int i = 0; i < size; i++) {
            list[i] = null;
        }
        size = 0;
    }

    @Override
    public T get(int index) {
        return (T) list[index];
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += list[i].toString() + "; ";
        }
        return result;
    }
}

