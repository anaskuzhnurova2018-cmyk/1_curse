package Threads;

public class TaskRunnableMain {
    public static void main(String[] args) throws InterruptedException {

        int countProc = Runtime.getRuntime().availableProcessors();
        System.out.println(countProc);

        System.out.println("Начало работы");
        Thread task1 = new Thread(new TaskRunnable("Выполняется задача 1"));
        Thread task2 = new Thread(new TaskRunnable("Выполняется задача 2"));
        Thread task3 = new Thread(new TaskRunnable("Выполняется задача 3"));

        Thread task4 = new Thread(() -> {
            String message = "Выполняется задача 4";
            for (int i = 0; i < message.length(); i++) {
                System.out.println(message.charAt(i));
            }
            System.out.println();
        });

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
