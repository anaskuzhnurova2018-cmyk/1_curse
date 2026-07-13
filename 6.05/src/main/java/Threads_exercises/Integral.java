package Threads_exercises;

//класс, управляющий потоками

public class Integral {
    private double a;
    private double b;
    private int numThreads;
    public double[] partialSums;
    public double totalSum = 0;

    public Integral(double a, double b, int numThreads) {
        this.a = a;
        this.b = b;
        this.numThreads = numThreads;
        this.partialSums = new double[numThreads];
    }

    public void calculate() throws InterruptedException {
        totalSum = 0;
        //старт потоков
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[numThreads];
        for(int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new PartIntegralSum(a, b, i, numThreads, partialSums));
            threads[i].start();
        }

        for (Thread thr : threads) { //вызываем метод join
            thr.join();
        }

        long end = System.currentTimeMillis();
        System.out.println("Время: " + (end - start) + "мс");

        for (double s : partialSums) {
            totalSum += s;
        }
    }

    public double getTotalSum() {
        return totalSum;
    }

    public double calculateSingleThread() {
        long start = System.currentTimeMillis();

        //создаем поток для всей работы
        double[] singleRes = new double[1];
        PartIntegralSum singleTask = new PartIntegralSum(a, b, 0, 1, singleRes);

        singleTask.run();

        double res = singleRes[0];

        long end = System.currentTimeMillis();
        System.out.println("Время: " + (end - start) + "мс");

        return res;
    }
}
