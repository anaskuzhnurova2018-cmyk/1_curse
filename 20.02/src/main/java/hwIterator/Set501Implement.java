package hwIterator;

import java.util.Arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Set501Implement<T> implements Set501<T>, Iterable<T> {
    private Node<T>[] set = new Node[16];
    private int size = 0;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = null;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (current != null && current.getNext() != null) {
                    return true;
                }

                for (int i = index; i < set.length; i++) {
                    if (set[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public T next() {
                if (current != null && current.getNext() != null) {
                    return current.getNext().getValue();
                }

                while (index < set.length) {
                    if (set[index] != null) {
                        current = set[index];
                        index++;
                        return current.getValue();
                    }
                    index++;
                }
                throw new NoSuchElementException();
            }
        };
    }

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
        int hash = o.hashCode();
        int index = Math.abs(hash % 16);
        Node<T> current = set[index];
        while (current != null) {
            if (o.equals(current.getValue())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void add(T o) {
        if (!contains(o)) {
            int hash = o.hashCode();
            int index = Math.abs(hash % 16);
            Node<T> newNode = new Node<>(o);
            if (set[index] == null) {
                set[index] = newNode;
            } else {
                newNode.setNext(set[index]); //указываем на старый элемент
                set[index] = newNode; //новый узел в начало списка
            }
            size++;
        }
    }

    @Override
    public void delete(T o) {
        if (! (o == null)) {
            if (contains(o)) {
                int index = Math.abs(o.hashCode() % 16);
                Node<T> current = set[index];
                Node<T> prev = null;
                if (current.getValue().equals(o)) {
                    set[index] = set[index].getNext();
                } else {
                    while (current != null) {
                        if (current.getValue().equals(o)) {
                            prev.setNext(current.getNext());
                            break;
                        }
                        prev = current;
                        current = current.getNext();
                    }
                }
                size--;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public void clean() {
        for (int i = 0; i < set.length; i++) {
            set[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        Object[] allElements = getAll();
        return "Set{" + "elements" + Arrays.toString(allElements) + ", size=" + size + '}';
    }

    @Override
    public Object[] getAll() {
        Object[] result = new Object[size];
        int index = 0;
        for (int i = 0; i < set.length; i++) {
            Node<T> current = set[i];
            while (current != null) {
                result[index++] = current.getValue();
                current = current.getNext();
            }
        }
        return result;
    }
}
