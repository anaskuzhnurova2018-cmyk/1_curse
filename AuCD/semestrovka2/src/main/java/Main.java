import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    private static final int TOTAL_INSERTS = 10000;
    private static final int SEARCH_COUNT = 100;
    private static final int DELETE_COUNT = 1000;
    public static void main(String[] args) {
        Random rand = new Random(42);
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < TOTAL_INSERTS) {
            uniqueNumbers.add(rand.nextInt(100_000));
        }
        List<Integer> dataArray = new ArrayList<>(uniqueNumbers);
        Collections.shuffle(dataArray, new Random(42)); //перемешиваем порядок вставки

        System.out.println("Данные сгенирированы: " + dataArray.size() + " уникальных чисел");
        runInsertMain(dataArray);
        runSearchMain(dataArray);
        runDeleteMain(dataArray);
    }

    private static void runInsertMain(List<Integer> data) {
        TwoThreeTree tree = new TwoThreeTree();

        List<Long> insertTime = new ArrayList<>(); //время в наносекундах
        List<Long> insertOps = new ArrayList<>(); //кол-во операций

        System.out.println("Замер вставки " + data.size() + " элементов...");
        long totalStart = System.nanoTime();

        for (int value : data) {
            tree.resetOperations();
            long start = System.nanoTime();

            tree.insert(value);

            long end = System.nanoTime();

            insertTime.add(end - start);
            insertOps.add((long) tree.getOperations());
        }

        long totalEnd = System.nanoTime();

        double avgTime = average(insertTime);
        double avgOps = average(insertOps);
        long totalTimeMs = (totalEnd - totalStart) / 1_000_000;

        System.out.println("Вставка завершена");
        System.out.println("Общее время: " + totalTimeMs + " мс");
        System.out.println("Среднее время на вставку: " + String.format("%.2f", avgTime));
        System.out.println("Среднее число операций: " + String.format("%.2f", avgOps));

        saveResults("insert_times.csv", insertTime);
        saveResults("insert_ops.csv", insertOps);
    }

    private static double average(List<Long> list) {
        return list.stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0);
    }

    private static void runSearchMain(List<Integer> data) {
        TwoThreeTree tree = new TwoThreeTree();
        for (int value : data) {
            tree.insert(value);
        }

        //выбираем 100 случайный элементов
        Random rand = new Random(42);
        List<Integer> searchSamples = new ArrayList<>();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            searchSamples.add(data.get(rand.nextInt(data.size())));
        }

        List<Long> searchTimes = new ArrayList<>();
        List<Long> searchOps = new ArrayList<>();
        int foundCount = 0;

        System.out.println("Замер поиска: " + SEARCH_COUNT + " элементов");

        for (int value : searchSamples) {
            tree.resetOperations();
            long start = System.nanoTime();

            boolean found = tree.search(value);
            if (found) foundCount++;

            long end = System.nanoTime();

            searchTimes.add(end - start);
            searchOps.add((long) tree.getOperations());
        }

        double avgTime = average(searchTimes);
        double avgOps = average(searchOps);

        System.out.println("Поиск завершен");
        System.out.println("Найдено: " + foundCount + "/" + SEARCH_COUNT);
        System.out.println("Среднее время " + String.format("%.2f", avgTime));
        System.out.println("Среднее кол-во операций: " + String.format("%.2f", avgOps));

        saveResults("search_times.csv", searchTimes);
        saveResults("search_ops.csv", searchOps);
    }

    private static void runDeleteMain(List<Integer> data) {
        TwoThreeTree tree = new TwoThreeTree();
        for (int value : data) {
            tree.insert(value);
        }

        Random rand = new Random(42);
        Set<Integer> deleteSet = new HashSet<>();
        while (deleteSet.size() < DELETE_COUNT) {
            deleteSet.add(data.get(rand.nextInt(data.size())));
        }

        List<Integer> deleteSamples = new ArrayList<>(deleteSet);

        List<Long> deleteTimes = new ArrayList<>();
        List<Long> deleteOps = new ArrayList<>();
        int deleteCount = 0;

        System.out.println("Замер удаления " + DELETE_COUNT + " элементов");

        for (int value : deleteSamples) {
            tree.resetOperations();
            long start = System.nanoTime();

            boolean result = tree.delete(value);
            if (result) deleteCount ++;

            long end = System.nanoTime();

            deleteTimes.add(end - start);
            deleteOps.add((long) tree.getOperations());
        }

        double avgTime = average(deleteTimes);
        double avgOps = average(deleteOps);

        System.out.println("Удаление завершено");
        System.out.println("Удалено: " +  deleteCount + "/" + DELETE_COUNT);
        System.out.println("Среднее время: " + String.format("%.2f", avgTime));
        System.out.println("Среднее кол-во операций: " + String.format("%.2f", avgOps));

        saveResults("delete_times.csv", deleteTimes);
        saveResults("delete_ops.csv", deleteOps);
    }

    private static void saveResults(String fileName, List<Long> values) {
        try(PrintWriter writer = new PrintWriter(fileName)) {
            for (long val : values) {
                writer.println(val);
            }
            System.out.println("Данные сохранены в " + fileName);
        } catch (FileNotFoundException ex) {
            System.err.println("Ошибка записи в файл: " + fileName);
        }
    }
}
