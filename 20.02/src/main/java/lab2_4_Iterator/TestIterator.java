package lab2_4_Iterator;

public class TestIterator {
    public static void main(String[] args) {
        List501Implements<String> list = new List501Implements<>();
        //превратить в итерируемый объект множества, которые мы делали с hash, hashSet; hashset должен реализовать интерфейс iterable , в котором мы реализуем оператор
        list.add("1");
        list.add("2");
        list.add("3");

        for (String s : list) {
            System.out.println(s);
        }

    }
}

