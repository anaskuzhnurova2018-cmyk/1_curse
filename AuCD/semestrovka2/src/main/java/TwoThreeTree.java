import java.util.Arrays;

public class TwoThreeTree {
    private Node root;
    private int operations = 0;

    public TwoThreeTree() {
        this.root = null;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getOperations() {
        return operations;
    }

    public void resetOperations() {
        this.operations = 0;
    }

    public boolean search(int key) { //алгоритм поиска
        Node current = root;

        if (current == null) {
            return false;
        }

        while (current != null) {
            operations++;
            if (current.isLeaf()) {
                for (int i = 0; i < current.getCountKey(); i++) {
                    operations++;
                    if (key == current.getKey()[i]) {
                        return true;
                    }
                }
                return false; //не нашли в листе
            } else {
                if (current.getCountKey() == 1) { //1 ключ
                    operations++;
                    if (key < current.getKey()[0]) { //искомый ключ меньше разделителя
                        current = current.getChildren()[0]; //идем в левое поддерево, дальше повторяем цикл с новым значением current
                    } else if (key > current.getKey()[0]) {
                        current = current.getChildren()[1];
                    } else {
                        return true; //ключ равен разделителю
                    }
                } else { //2 ключа
                    operations++;
                    if (key < current.getKey()[0]) {
                        current = current.getChildren()[0];
                    } else if (key > current.getKey()[1]) {
                        current = current.getChildren()[2];
                    } else {
                        operations++;
                        if (key == current.getKey()[0] || key == current.getKey()[1]) { //проверяем на совпадение с ключами
                            return true;
                        }
                        current = current.getChildren()[1];
                    }
                }
            }
        }
        return false;
    }

    //вспомогательный узел для обратки сплита
    private static class SplitResult {
        Node leftChild;
        Node rightChild;
        int medianKey; //ключ, который всплывает в родителя
        boolean isSplit;

        public SplitResult(Node leftChild, Node rightChild, int medianKey, boolean isSplit) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.medianKey = medianKey;
            this.isSplit = isSplit;
        }

        public SplitResult() {
            this.isSplit = false;
        }

        public SplitResult(Node leftChild, Node rightChild, int medianKey) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.medianKey = medianKey;
        }
    }

    public void insert(int key) { //метод вставки
        resetOperations();

        if (root == null) {
            root = new Node(key);
            return;
        }

        SplitResult result = insertRecursive(root, key);

        //если корень сплитуется, то создаем новый корень
        if (result.isSplit) {
            Node newRoot = new Node(result.medianKey, result.leftChild, result.rightChild);
            newRoot.setCountKey(1);
            newRoot.setLeaf(false);
            root = newRoot;
        }
    }

    private SplitResult insertRecursive(Node node, int key) {
        operations++;
        //базовый случай: мы в листе
        if (node.isLeaf()) {
            return insertIntoLeaf(node, key);
        }

        //рекурсивный случай: внутренний узел
        //определяем, в какого ребенка спускаться
        int childIndex = findChildIndex(node, key);

        //рекурсивно вставляем в ребенка
        SplitResult result = insertRecursive(node.getChildren()[childIndex], key);

        //если ребенко сплитнулся - обрабатываем всплытие
        if (result.isSplit) {
            return handleSplit(node, childIndex, result);
        }

        //если не было сплита, то все хорошо
        return new SplitResult();
    }

    private SplitResult insertIntoLeaf(Node leaf, int key) {
        if (leaf.getCountKey() == 1) { //есть место - просто добавляем вторый ключ (сортируем)
            if (key < leaf.getKey()[0]) {
                leaf.getKey()[1] = leaf.getKey()[0];
                leaf.getKey()[0] = key;
            } else {
                leaf.getKey()[1] = key;
            }
            leaf.setCountKey(2);
            return new SplitResult(); //сплита не было
        } else {
            //лист переполнен (2 ключа) -> нужен сплит
            //собираем три ключа и сортируем
            int[] keys = new int[3];
            keys[0] = leaf.getKey()[0];
            keys[1] = leaf.getKey()[1];
            keys[2] = key;
            Arrays.sort(keys);

            Node left = new Node(keys[0]);
            Node right = new Node(keys[2]);

            return new SplitResult(left, right, keys[1], true);
        }
    }

    private int findChildIndex(Node node, int key) { //куда спускаться
        if (node == null) return -1;

        if (node.getCountKey() == 1) {
            if (key < node.getKey()[0]) {
                return 0; //левый ребенок
            } else {
                return 1; //правый
            }
        } else {
            if (key < node.getKey()[0]) {
                return 0;
            } else if (key > node.getKey()[1]) {
                return 2;
            } else {
                return 1;
            }
        }
    }

    private SplitResult handleSplit(Node parent, int childIndex, SplitResult childResult) {
        //обработка всплытия родителя
        //у родителя 1 ключ, он просто вместит всплывший ключ
        if (parent.getCountKey() == 1) {
            int m = childResult.medianKey;
            Node left = childResult.leftChild;
            Node right = childResult.rightChild;

            //1 вставляем медианный ключ в отсортированным порядке
            if (m < parent.getKey()[0]) {
                parent.getKey()[1] = parent.getKey()[0];
                parent.getKey()[0] = m;
            } else {
                parent.getKey()[1] = m;
            }

            //2 обновляем ссылки на детей (изначально2 ребенка, мы заменяем children[childIndex] на два новых left и right
            if (childIndex == 0) {
                //сдвиг старого правого ребенка вправо
                parent.getChildren()[2] = parent.getChildren()[1];
                parent.getChildren()[1] = right;
                parent.getChildren()[0] = left;
            } else {
                parent.getChildren()[2] = right;
                parent.getChildren()[1] = left;
            }
            parent.setCountKey(2);
            parent.setLeaf(false); //теперь это внутренний узел
            return new SplitResult(); //дальше сплит не всплывает
        } else {
            //у родителя 2 ключа, тогда переполнение будет
            int[] tempKeys = new int[3];
            Node[] tempChildren = new Node[4];

            for (int i = 0; i < childIndex; i++) {
                tempKeys[i] = parent.getKey()[i];
                tempChildren[i] = parent.getChildren()[i];
            }

            //вставляем всплывший ключ и двух новых детей
            tempKeys[childIndex] = childResult.medianKey;
            tempChildren[childIndex] = childResult.leftChild;
            tempChildren[childIndex + 1] = childResult.rightChild;

            //копируем оставшиеся элементы после позиции разделения
            for (int i = childIndex; i < parent.getCountKey(); i++) {
                tempKeys[i + 1] = parent.getKey()[i];
                tempChildren[i + 2] = parent.getChildren()[i + 1];
            }

            //соритруем ключи, чтобы найти новый медианный
            Arrays.sort(tempKeys);
            int newMedian = tempKeys[1];

            //создаем новый левый узел (ключ + первые два ребенка)
            Node newLeft = new Node(tempKeys[0]);
            newLeft.setCountKey(1);
            newLeft.setLeaf(false);
            newLeft.getChildren()[0] = tempChildren[0];
            newLeft.getChildren()[1] = tempChildren[1];

            //создаем новый правый узел (ключ + последние два ребенка)
            Node newRight = new Node(tempKeys[2]);
            newRight.setCountKey(1);
            newRight.setLeaf(false);
            newRight.getChildren()[0] = tempChildren[2];
            newRight.getChildren()[1] = tempChildren[3];

            //возвращаем результат, чтобы рекурсия обработала сплит на уровне выше
            return new SplitResult(newLeft, newRight, newMedian, true);
        }
    }

    //результат обработки (ключей недостает)
    private static class underflowResult {
        Node mergeNode; //уел после слияния
        int borrowedKey; //ключ, который нужно удалить из родителя
        boolean needsFix; //нужно ли продолжать фикс наверх
        boolean isBorrowed; //произошло ли заимствование

        //устранено (все ок)
        public underflowResult() {
            this.needsFix = false;
            this.isBorrowed = false;
        }

        //произошло слияние, нужно исправить родителя
        public underflowResult(Node merged, int keyToRemove) {
            this.mergeNode = merged;
            this.borrowedKey = keyToRemove;
            this.needsFix = true;
            this.isBorrowed = false;
        }

        //заимствование у соседа
        public underflowResult(boolean borrowed) {
            this.needsFix = false;
            this.isBorrowed = borrowed;
        }
    }

    public boolean delete(int key) {
        resetOperations();
        if (root == null) return false;

        boolean found = deleteRecursive(root, null, -1, key);

        //если корень опустел после слияния
        if (root.getCountKey() == 0 && !root.isLeaf()) {
            root = root.getChildren()[0];
        }
        return found;
    }

    private boolean deleteRecursive(Node node, Node parent, int childIndex, int key) {
        if (node == null) {
            return false;
        }

        operations++;

        //ищем ключ в текущем узле
        int keyPos = findKeyPosition(node, key);

        if (keyPos != -1) {
            if (node.isLeaf()) {
                removeKeyFromNode(node, keyPos);
                checkUnderflow(node, parent, childIndex);
                return true;
            } else {
                return deleteInternalKey(node, keyPos, key);
            }
        } else { //спускаемся в поддерево
            if (node.isLeaf()) return false;

            int childIdx = findChildIndex(node, key);
            Node child = node.getChildren()[childIdx];
            boolean found = deleteRecursive(node.getChildren()[childIdx], node, childIdx, key);

            if (found && child != null) {
                checkUnderflow(child, node, childIdx);
            }
            return found;
        }
    }

    private int findKeyPosition(Node node, int key) {
        if (node == null) return -1;

        for (int i = 0; i < node.getCountKey(); i++) {
            operations++;
            if (node.getKey()[i] == key) return i;
        }
        return -1;
    }

    private void removeKeyFromNode(Node node, int pos) {
        //сдвигаем ключи влево
        for (int i = pos; i < node.getCountKey() - 1; i++) {
            node.getKey()[i] = node.getKey()[i + 1];
        }
        node.getKey()[node.getCountKey() - 1] = 0;
        node.setCountKey(node.getCountKey() - 1);
    }

    //замена ключа на наследника
    private boolean deleteInternalKey(Node node, int keyPos, int key) {
        //наследник - наименьший ключ в правом поддереве
        Node successorParent = null;
        Node current = node.getChildren()[keyPos + 1];

        if (current == null) {
            return false;
        }

        while (!current.isLeaf()) {
            successorParent = current;
            current = current.getChildren()[0]; //идем влево до листа
        }

        //копируем наследникав удаляемую позицию
        node.getKey()[keyPos] = current.getKey()[0];

        //рекурсивно удаляем наследника из листа
        return deleteRecursive(current, successorParent, 0, current.getKey()[0]);
    }

    private boolean checkUnderflow(Node node, Node parent, int childIdx) {
        if (node == null) return true;
        if (node.getCountKey() >= 1) return true; //все хорошо, ключи есть

        if (parent == null) return true; //это корень

        //пытаемся одолжить у левого соседа
        if (childIdx > 0) {
            Node leftSibling = parent.getChildren()[childIdx - 1];
            if (leftSibling != null && leftSibling.getCountKey() == 2) {
                borrowFromLeft(node, parent, childIdx);
                return true;
            }
        }

        //пытаемся одолжить у правого соседа
        if (childIdx < parent.getCountKey()) {
            Node rightSibling = parent.getChildren()[childIdx + 1];
            if (rightSibling != null && rightSibling.getCountKey() == 2) {
                borrowFromRight(node, parent, childIdx);
                return true;
            }
        }

        //не удалось одолжить -> слияние с соседом
        return mergeWithSibling(node, parent, childIdx);
    }

    private void borrowFromLeft(Node node, Node parent, int childIdx) {
        Node leftSibling = parent.getChildren()[childIdx - 1];

        //спускаем ключ из родителя в текущий узел
        node.getKey()[1] = node.getKey()[0];
        node.getKey()[0] = parent.getKey()[childIdx - 1];

        //если не лист - перемещаем ребенка
        if (!node.isLeaf()) {
            node.getChildren()[2] = node.getChildren()[1];
            node.getChildren()[1] = node.getChildren()[0];
            node.getChildren()[0] = leftSibling.getChildren()[2];
        }

        //поднимаем ключ из левого соседа в родителя
        parent.getKey()[childIdx - 1] = leftSibling.getKey()[1];

        node.setCountKey(2);
        leftSibling.setCountKey(1);
    }

    private void borrowFromRight(Node node, Node parent, int childIdx) {
        Node rightSibiling = parent.getChildren()[childIdx + 1];

        int separatorKey = parent.getKey()[childIdx];

        node.getKey()[0] = separatorKey;
        parent.getKey()[childIdx] = rightSibiling.getKey()[0];

        //если не лист - перемещаем ребенка
        if (!node.isLeaf()) {
            node.getChildren()[1] = rightSibiling.getChildren()[0];
            rightSibiling.getChildren()[0] = rightSibiling.getChildren()[1];
            rightSibiling.getChildren()[1] = rightSibiling.getChildren()[2];
            rightSibiling.getChildren()[2] = null;
        }

        node.setCountKey(1);
        rightSibiling.setCountKey(1);
    }

    private boolean mergeWithSibling(Node node, Node parent, int childIdx) {
        Node sibling;
        int parentKeyIdx;

        //выбираем соседа (лучше левого)
        if (childIdx > 0) {
            sibling = parent.getChildren()[childIdx - 1];
            if (sibling == null) return true;
            parentKeyIdx = childIdx - 1;
        } else {
            sibling = parent.getChildren()[1];
            if (sibling == null) return true;
            parentKeyIdx = 0;
        }

        //собираем ключи (родитель+сосед+текущий узел)
        int[] mergeKeys = new int[3];
        mergeKeys[0] = sibling.getKey()[0];
        mergeKeys[1] = parent.getKey()[parentKeyIdx];
        mergeKeys[2] = (node.getCountKey() > 0) ? node.getKey()[0] : 0;

        //копируем соседа
        sibling.getKey()[0] = mergeKeys[0];
        sibling.getKey()[1] = mergeKeys[1];
        sibling.setCountKey(2);

        //если не листья - переносим детей
        if (!node.isLeaf()) {
            if (childIdx > 0) {
                sibling.getChildren()[2] = node.getChildren()[0];
                sibling.getChildren()[3] = node.getChildren()[1];
            } else {
                sibling.getChildren()[3] = sibling.getChildren()[1];
                sibling.getChildren()[2] = sibling.getChildren()[0];

                sibling.getChildren()[0] = node.getChildren()[0];
                sibling.getChildren()[1] = node.getChildren()[1];
            }
        }

        for (int i = parentKeyIdx; i < parent.getCountKey() - 1; i++) {
            parent.getKey()[i] = parent.getKey()[i + 1];
        }

        parent.setCountKey(parent.getCountKey() - 1);

        int emptyNodeIndex = (childIdx > 0) ? childIdx : 0;

        for (int i = emptyNodeIndex; i < parent.getCountKey() + 1; i++) {
            if (i + 1 < parent.getChildren().length) {
                parent.getChildren()[i] = parent.getChildren()[i + 1];
            }
        }

        parent.getChildren()[parent.getCountKey() + 1] = null;

        return parent.getCountKey() == 0 && parent != root;
    }
}
