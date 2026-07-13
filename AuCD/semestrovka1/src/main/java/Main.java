import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        File[] files = new File(".").listFiles((d, name) -> name.startsWith("graph_") && name.endsWith(".txt"));

        //проходимся по файлу
        for (File file : files) {
            Graph graph = GraphLoader.loadFromFile(file.getAbsolutePath());

            TopologicalSort.SortResult result = TopologicalSort.sort(graph);

            System.out.println(result.verticesCount + ";" +
                            result.timeNanos + ";" +
                            result.iterations + ";" +
                            result.success);
        }
    }
}
