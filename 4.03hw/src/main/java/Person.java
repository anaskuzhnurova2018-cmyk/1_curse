public class Person {
    private String name;
    private Gender gender;

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!o.getClass().equals(this.getClass())) return false;
        Person p = (Person) o;
        return p.gender == this.gender && ((p.name == null && this.name == null) || (p.name != null && p.name.equals(this.name)));
    }

    @Override
    public int hashCode() {
        return 31 * name.hashCode() + gender.hashCode();
    }

    @Override
    public String toString() {
        return "P{" +
                "n='" + name + '\'' +
                ", g=" + gender +
                '}';
    }

    public int compareTo(Person o2) {
        return this.name.compareTo(o2.name);
    }
}
