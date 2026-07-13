import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Test {
    public static void main(String args[]) {
        Set501<String> set = new Set501Implements<>();

        set.add("1");
        set.add("333");
        set.add("44");
        set.add("345");
        set.add("5456");
        set.add("6666");

        Set501<String> set2 = set.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() < 3;
            }
        });

        for(Object s : set2.getAll()) {
            System.out.println(s);
        }

        List<Integer> list = set.collect(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });

        for (Integer s : list) {
            System.out.println(s);
        }

        List<String> list2 = set.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for(String s : list2) {
            System.out.println(s);
        }

    }
}
