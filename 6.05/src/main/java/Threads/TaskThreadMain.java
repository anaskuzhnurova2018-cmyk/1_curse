package Threads;

public class TaskThreadMain {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Начало работы");
        Thread task1 = new TaskThread("Выполняется задача 1");
        Thread task2 = new TaskThread("Выполняется задача 2");
        Thread task3 = new TaskThread("Выполняется задача 3");

        //запуск start() создает поток и запускает run()
        task1.start();
        task2.start();
        task3.start();

        task1.join(); //join() блокирует поток main до завершения task1
        task2.join();
        task3.join();

        System.out.println("Завершаем программу");
    }
}
