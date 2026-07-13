public class Node {
    private int[] key = new int[2];
    private Node[] children = new Node[4];
    private int countKey = 0;
    private boolean isLeaf = true;

    //конструктор для листа
    public Node(int key) {
        this.key[0] = key;
        this.countKey = 1;
        this.isLeaf = true;
    }

    //конструктор для внутрненнего узла
    public Node(int key, Node left, Node right) {
        this.key[0] = key;
        this.children[0] = left;
        this.children[1] = right;
        this.countKey = 1;
        this.isLeaf = false;
    }

    public int[] getKey() {
        return key;
    }

    public void setKey(int[] key) {
        this.key = key;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public int getCountKey() {
        return countKey;
    }

    public void setCountKey(int countKey) {
        this.countKey = countKey;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
