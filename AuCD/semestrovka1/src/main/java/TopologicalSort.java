/*
находим все вершины с inDegree = 0 (нет входящих ребер)
добавляем их в очередь
пока очередь не пуста:
-извлекаем вершину,
-добавляем в результат,
-уменьшаем inDegree всех ее соседей,
-если inDegree соседа стал = 0 -> добавляем его в очередь
* */

import java.util.*;

public class TopologicalSort {
    public static class SortResult {
        public int verticesCount;
        public long timeNanos; //время выполнения
        public int iterations;
        public boolean success;
        public List<Integer> order;

        public SortResult(int verticesCount, long timeNanos, int iterations, boolean success, List<Integer> order) {
            this.verticesCount = verticesCount;
            this.timeNanos = timeNanos;
            this.iterations = iterations;
            this.success = success;
            this.order = order;
        }
    }

    public static SortResult sort(Graph graph) {
        int n = graph.getVerticesCount();

        int[] inDegree = Arrays.copyOf(graph.getInDegree(), n);

        //очередь для вершин с нулевой входящей степенью
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        //список для хранения рез-та сортировки
        List<Integer> sortedOrder = new ArrayList<>();

        int iterations = 0;

        //начало замера времени
        long startTime = System.nanoTime();

        while(!queue.isEmpty()) {
            int u = queue.poll(); //извлекли вершину
            sortedOrder.add(u);
            iterations++;

            //проходим по соседям вершины
            for (int v : graph.getAdjacencyList().get(u)) {
                inDegree[v]--;

                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        //конец замера времени
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        //проверяем, нет ли цикла
        boolean success = (sortedOrder.size() == n);

        return new SortResult(n, duration, iterations, success, sortedOrder);
    }
}
