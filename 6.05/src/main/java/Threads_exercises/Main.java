package Threads_exercises;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        double a = -1;
        double b= 1;
        int numThreads = Runtime.getRuntime().availableProcessors();

        Integral integral = new Integral(a, b, numThreads);

        //однопоточное вычисление
        double singleRes = integral.calculateSingleThread();
        System.out.println("Result (single thread): " + singleRes);
        //многопоточное
        integral.calculate();
        System.out.println("Результат (many threads): " + integral.getTotalSum());
        System.out.println("Разница: " + Math.abs(singleRes - integral.getTotalSum()));
    }
}
