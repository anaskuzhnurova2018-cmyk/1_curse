import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//поиск ребер, входящих в кратчайшие пути (использовать обратный граф для вычисления расстояний от всех вершин до финиша)
//чтобы понять, что ребро u->v принадлежит хотя бы одному кратчайшему пути из A в B,
// нужно проверить условие: dist(A,u) + weight(u,v)+dist(v,B) == dist(A,B)
//dist(v,B) находим по обратному графу

public class Task8_11 {
    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(1, 2, 2),
                new Edge(2, 3, 8),
                new Edge(1, 3, 10),
                new Edge(1, 4, 1),
                new Edge(4, 3, 100)
        );

        List<Edge> result = optimizedNetWork(edges, 1, 3);

        for (Edge e : result) {
            System.out.println(e);
        }
    }

    public static List<Edge> optimizedNetWork(List<Edge> edges, int start, int end) {
        int[][] direct = getDirectMatrix(edges); //direct[i][j] хранит вес ребра i->j
        int[][] reverse = getReverseMatrix(edges);

        int[] distFromStart = dijkstra(direct, start); //расстояние от А до всех (distFromStart[v] = кратчайшее рас-ие от start до v
        int[] distToEnd = dijkstra(reverse, end);

        int minPath = distFromStart[end]; //длина кратчайшего пути А->B

        List<Edge> result = new ArrayList<>();

        for (Edge e : edges) {
                if (distFromStart[e.getFrom()] != Integer.MAX_VALUE && distToEnd[e.getTo()] != Integer.MAX_VALUE) { //проверка на достижимость (защита от переполнения Integer.MAX_VALUE
                if (distFromStart[e.getFrom()] + e.getWeight() + distToEnd[e.getTo()] == minPath) { //лежит ли ребро на кратчайшем пути?
                    result.add(e);
                }
            }
        }

        return result;
    }

    public static int[] dijkstra(int[][] graph, int start) {
        int[] dist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < dist.length; i++) { //инициализация: все рас-ия бесконечны, кроме старта
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        for (int i = 0; i < graph.length; i++) { //ищем ближайшие непосещенные вершины с мин рас-ем
            int minDist = Integer.MAX_VALUE;
            int u = -1;
            for (int j = 0; j < graph.length; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    u = j;
                }
            }
            //если u = -1, значит все достижимые вершины уже посещены

            if (u == -1) break;

            visited[u] = true;

            for (int v = 0; v < graph.length; v++) { //пути до соседних вершин (кратчайшие)
                if (graph[u][v] != Integer.MAX_VALUE) { //если есть ребро u->v и оно не бесконечное
                    if (dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }
        return dist;
    }

    public static int[][] getDirectMatrix (List<Edge> list) { //создаем матрицу смежности
        int maxVertex = 0; //находим макс номер вершины, чтобы знать размер матрицы
        for (int i = 0; i < list.size(); i++) {
            int to = list.get(i).getTo();
            int from = list.get(i).getFrom();
            if (maxVertex < to) {
                maxVertex = to;
            }
            if (maxVertex < from) {
                maxVertex= from;
            }
        }
        int[][] directMatrix = new int[maxVertex + 1][maxVertex + 1];

        for (int i = 0; i < directMatrix.length; i++) { //заполняем бесконечностью
            for (int j = 0; j < directMatrix[i].length; j++) {
                directMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < list.size(); i++) { //прямая матрица
            Edge edge = list.get(i);

            int from = edge.getFrom();
            int to = edge.getTo();
            int weight = edge.getWeight();

            directMatrix[from][to] = weight;
        }
        return directMatrix;
    }

    public static int[][] getReverseMatrix(List<Edge> list) {
        int maxVertex = 0;
        for (int i = 0; i < list.size(); i++) {
            int to = list.get(i).getTo();
            int from = list.get(i).getFrom();
            if (to > maxVertex) {
                maxVertex = to;
            }
            if (from > maxVertex) {
                maxVertex = from;
            }
        }

        int[][] reverseMatrix = new int[maxVertex + 1][maxVertex + 1];

        for (int i = 0; i < reverseMatrix.length; i++) {
            for (int j = 0; j < reverseMatrix[i].length; j++) {
                reverseMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            Edge edge = list.get(i);

            int from = edge.getFrom();
            int to = edge.getTo();
            int weight = edge.getWeight();

            reverseMatrix[to][from] = weight;
        }
        return reverseMatrix;
    }
}

/*
Есть n пунктов транспортировки товара.
m каналов переправки товаров между ними (i,j).
Стоимость переправки единицы товара по каналу,
связывающему пункты i и j составляет d[i][j].
Есть главный пункт отправки с номером A
и главный пункт получения с номером B.
Нужно оптимизировать транспортировочную сеть.
Исключить из сети часть каналов так,
чтобы любой путь из А в В совпадал по длине с длиной кратчайшего пути
из А в В в исходной сети.
Исключите максимальное число каналов,
но так, чтобы все пути кратчайшей длины,
которые были в исходной сети продолжали существовать.
Сложность O(|V|^2)
* */