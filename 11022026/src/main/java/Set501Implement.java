import java.util.Arrays;

public class Set501Implement implements Set501 {
    private Node[] set = new Node[16];
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
    public boolean contains(Object o) {
        int hash = o.hashCode();
        int index = Math.abs(hash % 16);
        Node current = set[index];
        while (current != null) {
            if (o.equals(current.getValue())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void add(Object o) {
        if (!contains(o)) {
            int hash = o.hashCode();
            int index = Math.abs(hash % 16);
            Node newNode = new Node(o);
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
    public void delete(Object o) {
        if (! (o == null)) {
            if (contains(o)) {
                int index = Math.abs(o.hashCode() % 16);
                Node current = set[index];
                Node prev = null;
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
            Node current = set[i];
            while (current != null) {
                result[index++] = current.getValue();
                current = current.getNext();
            }
        }
        return result;
    }
}
