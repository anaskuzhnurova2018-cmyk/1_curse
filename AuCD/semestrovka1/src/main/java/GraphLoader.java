//чтение графа из файла

import java.io.*;
import java.util.*;

public class GraphLoader {
    public static Graph loadFromFile(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        //читаем кол-во вершин
        String firstLine = reader.readLine();
        if (firstLine == null || firstLine.trim().isEmpty()) {
            reader.close();
            throw new IOException("Файл пуст или поврежден: " + filename);
        }
        //первая строка - кол-во вершин
        int n = Integer.parseInt(firstLine.trim()); //trim - удаляет пробелы в начале и конце строки

        Graph graph = new Graph(n);

        // построчно читаем ребра
        String line;
        while((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.trim().split("\\s+"); //разбиваем по пробелам

            //проверяем корректность строки
            if (parts.length == 2) {
                try {
                    int u = Integer.parseInt(parts[0]); //откуда
                    int v = Integer.parseInt(parts[1]); //куда

                    //добавляем ребро
                    graph.getAdjacencyList().get(u).add(v);

                    //увеличиваем кол-во входящих в вершину ребер
                    graph.getInDegree()[v]++;
                } catch (NumberFormatException e) {
                    System.out.println("Некорректная строка: " + line);
                }
            }
        }
        reader.close();
        return graph;
    }
}
