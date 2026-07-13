package lab2_7.generic;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class TestGeneric {
    public static void main(String args[]) {
        Set501Gen<String> set = new Set501Implement<>();

        set.add("1");
        set.add("2");
        set.add("3333");
        set.add("4");
        set.add("555555");
        set.add("66666666");

        Set501Gen<String> set2 = set.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 3;
            }
        });

        for (Object s : set2.getAll()) {
            System.out.println(s);
        }

        List<Integer> list1 = set.collect(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });

        for (Integer s : list1) {
            System.out.println(s);
        }

        List<String> list2 = set.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String s : list2) {
            System.out.println(s);
        }
    }
}
