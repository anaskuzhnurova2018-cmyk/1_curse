public class RandomGeneratorTask extends Thread {
    // методы для управления
    // synchronized setMax
    // synchronized getRandom
    // stop
    // start
    private int n; //максимальное значение
    private int k; //текущее значение

    public RandomGeneratorTask() { //начальные данные
        n = 1000;
        k = 0;
    }

    public synchronized void setMax(int n) { //задаём новое максимальное значение
        this.n = n;
    }

    public synchronized int getRandom() { //берём текущее значение
        return k;
    }

    public void run() {
        try {
            generate(); //запускаем генератор
        } catch (InterruptedException e) { //остановка потока
            System.out.println("Поток был остановлен");
        }

    }

    private void generate() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (k <= n) {
                    ++k;
                } else {
                    k = 0;
                }
            }
            Thread.sleep(500);
        }
    }

}
