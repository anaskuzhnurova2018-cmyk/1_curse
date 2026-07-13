package hwLinkedList;

public class Map501Impl<K, V> implements Map501<K, V> {

    private int size;
    private Entry501<K, V>[] entries = new Entry501[5];

    private class Entry501Impl<K, V> implements Entry501<K, V> {

        K key;
        V value;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key + " : " + value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Entry501Impl<K, V> entry = new Entry501Impl<>();
        entry.key = key;
        entry.value = value;
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)) {
                ((Entry501Impl<K, V>)entries[i]).value = value;
                return;
            }
        }
        if (size == entries.length) {
            Entry501<K, V>[] newEntries = new Entry501[entries.length * 2];
            for (int i = 0; i < size; i++) {
                newEntries[i] = entries[i];
            }
            entries = newEntries;
        }
        entries[size++] = entry;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)) {
                return entries[i].getValue();
            }
        }
        return null;
    }

    @Override
    public Set501<K> getKeySet() {
        Set501<K> keySet = new Set501Implement<>();
        for (int i = 0; i < size; i++) {
            keySet.add(entries[i].getKey());
        }
        return keySet;
    }

    @Override
    public List501<V> getValues() {
        List501<V> listValues = new List501Implements<>();
        for (int i = 0; i < size; i++) {
            listValues.add(entries[i].getValue());
        }
        return listValues;
    }

    @Override
    public List501<Entry501<K, V>> getEntries() {
        List501<Entry501<K, V>> lst = new List501Implements<>();
        for (int i = 0; i < size; i++) {
            lst.add(entries[i]);
        }
        return lst;
    }
}
