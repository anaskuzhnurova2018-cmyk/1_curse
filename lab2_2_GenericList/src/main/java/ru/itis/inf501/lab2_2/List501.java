package ru.itis.inf501.lab2_2;

public interface List501<T> {
    int size();
    boolean isEmpty();
    boolean isExist(T o);
    void add(T o);
    T delete(int index);
    void clean();
    T get(int index);
}
