package Lesson13;

import java.util.*;

public class Task13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] maze = new int[n][n];
        System.out.println("Введите лабиринт (0 - стена, 1 - проход):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j< n; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        System.out.println("Введите координаты старта:");
        int startI = sc.nextInt();
        int startJ = sc.nextInt();

        Point start = new Point(startI, startJ);

        if (maze[startI][startJ] == 0) {
            System.out.println("Стартовая клетка не проходима");
            return;
        }

        boolean found = canFindThePath(maze, start);

        if (!found) {
            System.out.println("Выхода из лабиринта нет");
        }

    }

    public static boolean isValid(int[][] maze, boolean[][] visited, Point p, int n) {
        if (p.getX() < 0 || p.getX() >= n || p.getY() < 0 || p.getY() >= n) return false; //проверка границ
        if (maze[p.getX()][p.getY()] == 0 || visited[p.getX()][p.getY()]) return false; //стена или тут уже были
        return true;
    }

    public static boolean canFindThePath (int[][] maze, Point start) {
        int n = maze.length;
        boolean[][] visited = new boolean[n][n];
        Point[][] parent = new Point[n][n];
        Queue<Point> queue = new LinkedList<>();

        queue.add(start);
        visited[start.getX()][start.getY()] = true;

        int[] dx = {0, 0, 1, -1}; // сдвиг по строке
        int[] dy = {1, -1, 0, 0}; // сдвиг по столбцу

        while (!queue.isEmpty()) {
            Point curr = queue.poll(); //удаляем первый элемент из очереди
            if (curr.getX() == 0 || curr.getX() == n-1 || curr.getY() == 0 || curr.getY() == n-1) { //проверяем выход ли это
                List<Point> path = reconstructPath(parent, curr);
                printThePath(path);
                return true;
            }

            for (int dir = 0; dir < 4; dir++) { //перебор 4-х соседей
                Point neighbour = new Point(
                        curr.getX() + dx[dir],
                        curr.getY() + dy[dir]
                );
                if (isValid(maze, visited, neighbour, n)) {
                    visited[neighbour.getX()][neighbour.getY()] = true; //посещенный сосед
                    parent[neighbour.getX()][neighbour.getY()] = curr; //записываем родителя
                    queue.add(neighbour);
                }
            }
        }
        return false;
    }

    public static List<Point> reconstructPath(Point[][] parent, Point exit) {
        List<Point> path = new ArrayList<>();
        Point current = exit;

        while(current != null) { //идем, пока не дойдем до старта, у него какр parent = null
            path.add(current);
            current = parent[current.getX()][current.getY()]; //переходим к родителю
        }
        return path;
    }

    public static void printThePath(List<Point> path) {
        for (Point p : path) {
            System.out.println("[" + p.getX() + ", " + p.getY() + "]");
        }
    }
}

/*
Лабиринт задан массивом a размером n´n,
в котором a[i, j]=1, если клетка “проходима”,
и a[i, j]=0 — если “непроходима”.
Путник изначально размещается в “проходимой” клетке [i0, j0].
Он может перемещаться в любую из соседних “проходимых клеток”,
если у нее есть общая сторона с той, в которой он находится.
Определить, может ли путник выйти из лабиринта.
Если может, то напечатать путь от выхода до начального положения путника.
Выходом считается любая граничная точка массива a.
Сложность O(n2)
* */