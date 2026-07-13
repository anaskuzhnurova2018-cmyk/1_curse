package hwLinkedList;

import java.util.NoSuchElementException;

public class Set501Implement<T> implements Set501<T> {
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
    public void add(Object o) {
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
}

