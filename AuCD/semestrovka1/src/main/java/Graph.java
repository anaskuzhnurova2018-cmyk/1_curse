// используем список смежности и массив входящих степеней

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int verticesCount;  // кол-во вершин
    private List<List<Integer>> adjacencyList;  // список смежности (куда идут стрелки, это и есть внутренний список)
    private int[] inDegree; // кол-во входящих ребер, храним отдельно, чтобы быстро находить вершины, готовые к обработке

    public Graph(int verticesCount) {
        this.verticesCount = verticesCount;
        this.adjacencyList = new ArrayList<>();
        this.inDegree = new int[verticesCount];

        // создает пустые внутренние списки для каждой вершины, чтобы потом можно было добавлять ребра
        for (int i = 0; i < verticesCount; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public List<List<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public int[] getInDegree() {
        return inDegree;
    }
}
