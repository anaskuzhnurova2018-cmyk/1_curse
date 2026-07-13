package ModuleTests;

public class MathUtil {
    public static int sum(int a, int b) {
        return a + b;
    }

    public static int compare(int a, int b) {
        return a - b;
    }

    public static int divide(int a, int b) throws IllegalArgumentException {
        if (b==0) {
            throw new IllegalArgumentException("Знаменатель не 0!");
        }

        return a/b;
    }

    public static int[] sort(int[] array) {
        return array;
    }
}
