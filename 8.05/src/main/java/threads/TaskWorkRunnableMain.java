package threads;

public class TaskWorkRunnableMain {
    public static double totalSum = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Начало работы");

        int countProc = Runtime.getRuntime().availableProcessors();

        Thread[] array = new Thread[countProc];

        for (int i = 0; i < countProc; i++) {
            array[i] = new Thread((new TaskWork(i)));
        }

        System.out.println("Запускаем потоки");
//        запуск start() создаёт поток и запускает метод run()
        for (int i = 0; i < countProc; i++) {
            array[i].start();
        }

        for (int i = 0; i < countProc; i++) {
            array[i].join();
        }

        Thread.sleep(1000); //создание задержки
        System.out.println("Завершаем программу с итогом " + TaskWorkRunnableMain.totalSum);
    }

    //    т.к. метод статичекский, то синхронизация произойдет на TaskWorkRunnableMain.class
    public synchronized static void putPartSum(double sum) {
        totalSum += sum;
    } //только один поток может одновременно выполнить этот метод
    //synchronized предотвращает одновременный доступ к общим данным
}
