package generic;

public interface Set501Gen<T> {
    int size();
    boolean checkEmpty();
    boolean contains(T o);
    void add(T o);
    void delete(T o);
    void clean();
    Object[] getAll();
}
