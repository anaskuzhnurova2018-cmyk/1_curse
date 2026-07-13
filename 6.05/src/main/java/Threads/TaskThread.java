package Threads;

/*
1) наследуемся от Threads, получая функционал создания и запуска отдельного потока
* */

public class TaskThread extends Thread{

    private String message;

    public TaskThread(String message) {
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
