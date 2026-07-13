package hwLinkedList;

public interface List501<T> {
    int size();
    boolean isEmpty();
    boolean contains(T o);
    void add(T o);
    T delete(int index);
    void clean();
    T get(int index);
}
