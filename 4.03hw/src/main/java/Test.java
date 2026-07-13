import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        List501<Person> list = new List501implement<>();

        list.add(new Person("Nastya", Gender.FEMALE));
        list.add(new Person("Masha", Gender.FEMALE));
        list.add(new Person("Lena", Gender.FEMALE));
        list.add(new Person("Danya", Gender.MALE));
        list.add(new Person("Sasha", Gender.MALE));
        list.add(new Person("Arthem", Gender.MALE));

        List501<Person> list2 = list.filter(new Predicate<Person>() {
            @Override
            public boolean test(Person p) {
                return p.getGender().equals(Gender.FEMALE);
            }
        });

        for(Object s : list2.getAll()) {
            System.out.println(s);
        }

        List<Integer> list3 = list.collect(new Function<Person, Integer>() {
            @Override
            public Integer apply(Person s) {
                return s.getName().length();
            }
        });

        for (Integer s : list3) {
            System.out.println(s);
        }

        List<Person> list4 = list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.compareTo(o2);
            }
        });
        for(Person s : list4) {
            System.out.println(s);
        }

    }
}
