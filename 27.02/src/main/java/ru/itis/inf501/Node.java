package ru.itis.inf501;

public class Node {
    private String name;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return ((Node) o).name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
