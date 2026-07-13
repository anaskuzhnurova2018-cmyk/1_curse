package ru.itis.inf501;


import java.util.*;

/*
ищем пустые зависимости и удаляем их, то есть постепенно опустошаем граф;
получение узла и просмотр его зависимостей и все это циклически; ищем пустые узлы
* */

public class Graph {
    private Map<Node, Set<Node>> map = new HashMap<>();

    public void add(Node node, Node ... dependencies) {
        map.put(node, new HashSet<>(List.of(dependencies)));
    }

    public Set<Node> findEmptyDependencies() {
        Set<Node> result = new HashSet<>();
        for (Map.Entry<Node, Set<Node>> entry : map.entrySet()) {
            if (entry.getValue().isEmpty()) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public void removeDependencies (Set<Node> nodes) {
        for (Node node : nodes) {
            map.remove(node);
        }

        for (Map.Entry<Node, Set<Node>> entry : map.entrySet()) {
            entry.getValue().removeAll(nodes);
        }
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}
