package ru.itis.inf501.lab2_2;

public class List501implement<T> implements List501<T> {
    private Node<T> head;
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
    public boolean isExist(T o) {
        if (head != null) {
            if (head.getValue().equals(o)) {
                return true;
            } else {
                Node<T> current = head.getNext();
                while (current != null) {
                    if (current.getValue().equals(o)) {
                        return true;
                    }
                    current = current.getNext();
                }
            }
        }
        return false;
    }

    @Override
    public void add(T o) {
        if (! isExist(o)) {
            Node<T> newNode = new Node<T>(o);
            newNode.setNext(head);
            head = newNode;
            size++;
        }
    }

    @Override
    public T delete(int index) {
        T result = null;
        if (!(index >= size)) {
            if (index == 0) {
                result = head.getValue();
                head = head.getNext();
                size--;
            } else {
                Node<T> current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.getNext();
                }
                Node<T> needToDelete = current.getNext();
                result = needToDelete.getValue();
                current.setNext(current.getNext().getNext());
                size--;
            }
        }
        return result;
    }

    @Override
    public void clean() {
        head = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        T result = null;
        if (!(index >= size)) {
            if (index == 0) {
                return head.getValue();
            } else {
                Node<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.getNext();
                }
                result = current.getValue();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.getValue());
                if (current.getNext() != null) {
                    sb.append(", ");
                }
                current = current.getNext();
            }
        sb.append("]");
        return sb.toString();
    }
}
