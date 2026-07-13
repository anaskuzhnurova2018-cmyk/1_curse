package lab2_4_Iterator;

import java.util.Iterator;

public class Students implements Iterable<String>, Iterator<String> {
    private String[] student = new String[5];

    private int current = 0;

    public Students(String student1, String student2, String student3, String student4, String student5) {
        this.student[0] = student1;
        this.student[1] = student2;
        this.student[2] = student3;
        this.student[3] = student4;
        this.student[4] = student5;
    }

    public Iterator<String> iterator() {
        current = 0;
        return this;
    }

    public boolean hasNext(){
        return current < student.length;
    }

    public String next() {
        return student[current++];
    }

    public static void main(String ... args) {
        Students students = new Students("1", "2", "3", "4", "5");

        /*while (students.hasNext()) {
            System.out.println(students.next());
        }*/

        for (String s : students) {
            System.out.println(s);
        }
    }

}
