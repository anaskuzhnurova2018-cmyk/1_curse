package ru.itis.inf501;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.add(new Node("d"), new Node[0]);
        graph.add(new Node("a"), new Node("d"), new Node("c"));
        graph.add(new Node("f"), new Node("d"));
        graph.add(new Node("g"), new Node("d"), new Node("c"), new Node("f"));
        graph.add(new Node("c"), new Node[0]);
        graph.add(new Node("b"), new Node("f"), new Node("g"), new Node("a"), new Node("c"));

        List<Node> result = new ArrayList<>();

        /*
        вместо result какую-нибудь класс-обертку нарисовать,
        где мы сделаем итератор отедельно,
        можно вместо List result использовать отдельный класс,
        в котором мы будем добавлять результаты сортировки (sorted Node)
        и внутри должен быть наш итератор;
        в цикле проходимся по элементам какой-то коллекции
        и внутри цикла что-то удаляем / добавляем, структура нарушается,
        вот это действие должно сопровождаться выбросом исключения
        * */

        while(!graph.isEmpty()) {
            Set<Node> empty = graph.findEmptyDependencies();
            graph.removeDependencies(empty);
            result.addAll(empty);
        }

        SortedNode sortedRes = new SortedNode(result);

        for (Node node : sortedRes) {
            System.out.println(node.getName());
            //sortedRes.add(new Node("test"));
        }
    }
}
