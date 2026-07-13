package ru.itis.inf501;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class SortedNode implements Iterable<Node> {
    private List<Node> sortedNodes; //хранит результаты сортировки
    int number = 0;

    public SortedNode(List<Node> initialNodes) {
        this.sortedNodes = new ArrayList<>(initialNodes);
    }

    public void add(Node node) {
        sortedNodes.add(node);
        number++;
    }

    public void remove(Node node) {
        sortedNodes.remove(node);
        number++;
    }


    @Override
    public Iterator<Node> iterator() {
        return new Iterator<Node>() {
            int current = 0;
            int expectedNumber = number;
            @Override
            public boolean hasNext() {
                checkForSize();
                return current < sortedNodes.size();
            }

            @Override
            public Node next() {
                checkForSize();
                return sortedNodes.get(current++);
            }

            public void checkForSize() {
                if (number != expectedNumber) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
