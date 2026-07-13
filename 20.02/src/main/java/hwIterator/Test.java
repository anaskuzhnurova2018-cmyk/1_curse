package hwIterator;

public class Test {
    public static void main(String args[]) {
        Set501<Person> set = new Set501Implement<>();
        //System.out.println(set.checkEmpty());
        set.add(new Person("I", Gender.MALE));
        set.add(new Person("O", Gender.FEMALE));
        set.add(new Person("T", Gender.MALE));
        set.add(new Person("H", Gender.FEMALE));
        set.add(new Person("Y", Gender.MALE));
        set.add(new Person("M", Gender.FEMALE));
        System.out.println(set);
        //System.out.println(set.contains(new Person("M", Gender.FEMALE)));
        /*set.delete(new Person("T", Gender.MALE));
        System.out.println(set);*/

        /*Object[] data = set.getAll();

        for (Object o: data) {
            System.out.println(((Person)o).getName());
        }*/
    }
}
