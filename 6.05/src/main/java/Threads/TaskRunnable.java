package Threads;

/*

* */

public class TaskRunnable implements Runnable {

    private String message;

    public TaskRunnable(String message) {
        super(); //перестраховочка
        this.message = message;
    }

    @Override
    public void run() {
        print();
    }

    private void print() {
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
        }
        System.out.println();
    }
}
