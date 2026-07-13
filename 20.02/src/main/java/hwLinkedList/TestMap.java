package hwLinkedList;

public class TestMap {
    public static void main(String[] args) {
        Map501<Integer, String> map = new Map501Impl<>();

        map.put(1, "Один");
        map.put(2, "Два");
        map.put(2, "Two");
        //System.out.println(map.size());

        //System.out.println(map.get(1));
        //System.out.println(map.get(3));

        /*Set501<Integer> keys = map.getKeySet();
        System.out.println(keys);
        System.out.println(keys.contains(0));
        System.out.println(keys.size());*/

        //System.out.println(map.getEntries());

        /*List501<String> values = map.getValues();
        System.out.println(values);
        System.out.println(values.delete(1));
        System.out.println(values);*/
    }
}
