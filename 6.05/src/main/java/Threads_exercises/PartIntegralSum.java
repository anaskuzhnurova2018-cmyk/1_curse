package Threads_exercises;

//каждый поток использует этот класс для вычисления своей части интеграла

public class PartIntegralSum implements Runnable {

    private double a;
    private double b;
    private int taskNumber; //номер потока
    private int totalTask; //общее кол-во потоков
    private double[] partialSums; //массив для записи результат

    public PartIntegralSum(double a, double b, int taskNumber, int totalTask, double[] partialSums) {
        this.a = a;
        this.b = b;
        this.taskNumber = taskNumber;
        this.totalTask = totalTask;
        this.partialSums = partialSums;
    }

    @Override
    public void run() {
        double segmentWidth = (b - a) / totalTask; //ширина сегмента для одного потока
        double width = (b - a) / 100; //ширина одного отрезка для сегмента
        double myStart = a + taskNumber * segmentWidth; //начальная точка интервала
        double mySum = 0;
        int pointsPerThread = 100 / totalTask;

        for (int i = 0; i < pointsPerThread; i++) { //100=N (по условию) - кол-во разбиений интервала
            double x = myStart + i * width; //конкретная точка внутри сегмента, в которой будем вычислять значение функции
            mySum += f(x) * width; //площадь = высота (f(x)) * ширину (width)
        }
        partialSums[taskNumber] = mySum;
    }

    public double f(double x) {
        return x * x * Math.exp(-Math.PI * x);
    }
}
