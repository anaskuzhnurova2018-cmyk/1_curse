package generic;

public class TestGeneric {
    public static void main(String args[]) {
        Set501Gen<Person> set = new Set501Implement<Person>();
        System.out.println(set.checkEmpty());
        set.add(new Person("I", Gender.MALE));
        set.add(new Person("O", Gender.FEMALE));
        set.add(new Person("T", Gender.MALE));
        set.add(new Person("H", Gender.FEMALE));
        set.add(new Person("Y", Gender.MALE));
        set.add(new Person("M", Gender.FEMALE));
        System.out.println(set);
        set.delete(new Person("T", Gender.MALE));
        System.out.println(set);

        System.out.println(new Person("M", Gender.FEMALE).hashCode());
        System.out.println(new Person("M", Gender.FEMALE).hashCode());

        Object[] data = set.getAll();

        for (Object o: data) {
            System.out.println(((Person)o).getName());
        }
    }
}
