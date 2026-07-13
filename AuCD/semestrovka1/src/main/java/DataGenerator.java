// чтобы граф не содержал циклов, мы создаем ребра от меньшего индекса к большему

import java.io.*;
import java.util.Random;

public class DataGenerator {
    public static void generateDatasets() {
        Random random = new Random(42); // Фиксированное зерно для воспроизводимости

        int startSize = 100;   // мин кол-во вершин
        int endSize = 10000;   // макс кол-во вершин
        int step = 200;

        for (int n = startSize; n <= endSize; n += step) {
            String filename = "graph_" + n + ".txt";
            createGraphFile(filename, n, random);
        }
  }
    private static void createGraphFile(String filename, int n, Random random) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            // кол-во вершин
            writer.write(n + "\n");

            // генерируем рёбра
            double edgeProbability = 0.05; // шанс создать ребро между вершинами

            for (int i = 0; i < n; i++) { //откуда идет ребро (вершина-источник)
                for (int j = i + 1; j < n; j++) { // куда идет ребро
                    // Создаём ребро i → j с нашей вероятностью
                    if (random.nextDouble() < edgeProbability) {
                        writer.write(i + " " + j + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
