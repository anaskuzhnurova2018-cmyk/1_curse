import lab2_7.Fruit;
import lab2_7.FruitComparator;
import lab2_7.FruitConsumer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;


public class TestFuncInterfaces {
    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("Яблоко", 140));
        fruits.add(new Fruit("Банан", 130));
        fruits.add(new Fruit("Груша", 200));
        fruits.add(new Fruit("Апельсин", 160));
        fruits.add(new Fruit("Гранат", 250));

        // Подаем объект, умеющий сравнивать Fruit
        fruits.sort(new FruitComparator());

        // Подаем объект, обрабатывающий Fruit
        // forEach перебирает все элементы по очереди, и применяет к ним accept
        fruits.forEach(new FruitConsumer());

        // используем анонимный класс
        fruits.sort(new Comparator<Fruit>() {
            public int compare(Fruit o1, Fruit o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });

        fruits.forEach(new Consumer<Fruit>() {
            public void accept(Fruit t) {
                System.out.println(t.getName() + " : " + t.getPrice());
            }
        });

        List<Integer> a = new ArrayList<>();
        a.replaceAll(new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return 0;
            }
        });

    }
}
