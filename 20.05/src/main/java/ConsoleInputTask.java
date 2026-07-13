import java.util.Scanner;

public class ConsoleInputTask extends Thread {

    // управляемый класс
    private final RandomGeneratorTask randomGeneratorTask;

    public ConsoleInputTask() {
        this.randomGeneratorTask = new RandomGeneratorTask();
    }

    public void run() {
        System.out.println("Начало работы программы");
        menu();
    }

    private void menu() {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("Введите команду (на выбор start, max, get, stop): ");
            command = scanner.nextLine();
            switch (command) {
                case ("start"):
                    System.out.println("Запуск потока");
                    randomGeneratorTask.start();
                    System.out.println("Поток запущен");
                    break;
                case ("max"):
                    System.out.println("Введите новое максимальное число:");
                    int max = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Число принято");
                    randomGeneratorTask.setMax(max);
                    System.out.println("Новый максимум - " + max + " - установлен");
                    break;
                case ("get"):
                    System.out.println("Текущее случайное число:");
                    System.out.println(randomGeneratorTask.getRandom());
                    break;
                case ("stop"):
                    System.out.println("Остановка программы");
                    randomGeneratorTask.interrupt();
                    return;
                default:
                    System.out.println("Неправильная команда, попробуйте ещё раз");
            }
        }
    }
}