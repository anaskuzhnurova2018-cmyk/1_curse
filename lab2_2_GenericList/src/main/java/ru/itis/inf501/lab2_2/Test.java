package ru.itis.inf501.lab2_2;

public class Test {
    public static void main(String[] args) {
        List501<Person> set = new List501implement<>();
        set.add(new Person("I", Gender.MALE));
        set.add(new Person("O", Gender.FEMALE));
        set.add(new Person("T", Gender.MALE));
        set.add(new Person("H", Gender.FEMALE));
        set.add(new Person("Y", Gender.MALE));
        set.add(new Person("M", Gender.FEMALE));
        System.out.println(set);
        //System.out.println(set.isEmpty());
        //System.out.println(set.isExist(new Person("M", Gender.FEMALE)));
        //set.delete(1);
        //System.out.println(set);
        //System.out.println(set.get(0));
    }
}
