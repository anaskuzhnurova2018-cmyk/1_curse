package generic;

import java.util.Arrays;

public class Set501Implement<T> implements Set501Gen<T> {
    private Object[] set = new Object[3];
    private int size = 0;

    private Node[] buckets;
    private static final int BUCKET_COUNT = 16;

    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    public Set501Implement() {
        buckets = new Node[BUCKET_COUNT];
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
        if (o == null) return false;

        int hash = o.hashCode();
        int index = Math.abs(hash % BUCKET_COUNT);
        int crc = index; //crc: xor с битами
        for (int i = 0; i < 4; i++) {
            crc = (crc << 1) ^ ((crc >> 31) & 1);
        }
        index = Math.abs(crc % BUCKET_COUNT);

        Node current = buckets[index];
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
        
    }

    @Override
    public void add(T o) {
        /*if (size == set.length) {
            Object[] newSet = new Object[set.length * 2];
            *//*for (int i = 0; i < set.length; i++) {
                newSet[i] = set[i];
            }*//*
            System.arraycopy(set, 0, newSet, 0, set.length);
            set = newSet;
        }
        set[size++] = o;*/

        if (o == null) return;

        if (contains(o)) return;

        if (size == set.length) {
            Object[] newSet = new Object[set.length * 2];
            System.arraycopy(set, 0, newSet, 0, set.length);
            set = newSet;
        }

        set[size++] = o;

        int hash = o.hashCode();
        int index = Math.abs(hash % BUCKET_COUNT);
        int crc = index;
        for (int i = 0; i < 4; i++) {
            crc = (crc << 1) ^ ((crc >> 31) & 1);
        }
        index = Math.abs(crc % BUCKET_COUNT);

        Node newNode = new Node(o);
        newNode.next = buckets[index];
        buckets[index] = newNode;
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
            throw new NotFoundException();
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
        size = 0;
    }

    @Override
    public String toString() {
        return "Set501Implement{" +
                "set=" + Arrays.toString(set) +
                ", size=" + size +
                '}';
    }

    @Override
    public Object[] getAll() {
        Object[] result = new Object[size];
        System.arraycopy(set, 0, result, 0, size);
        return result;
    }
}
