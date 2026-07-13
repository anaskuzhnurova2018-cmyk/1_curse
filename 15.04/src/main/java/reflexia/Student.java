package reflexia;

public class Student {
    public String name;
    private Integer age;
    private Group group;

    public Student() {}

    public Student(String name, Integer age, Group group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private void toUpper() {
        this.name = name.toUpperCase();
    }
}
