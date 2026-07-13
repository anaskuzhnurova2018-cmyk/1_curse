import java.util.*;

public class Task9_6 {
    public static void main(String[] args) {
        int[] x = {1, 2, 4, 10, 45, 55, 3, 6};
        int[] y = {2, 7, 8, 3, 55, 90, 87};

        int[] z = intersect(x, y);

        System.out.println(Arrays.toString(z));
    }

    //если элемент встречается в обоих массивах несколько раз, то в результат он попадает минимальное кол-во из двух

    public static int[] intersect(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map1 = new HashMap<>(); //ключ - число, значение - сколько раз встретилось
        for (int num : arr1) {
            map1.put(num, map1.getOrDefault(num, 0) + 1); //getOrDefault возвращает значение, соотвествующее указанному ключу, если он существует в карте. Если ключа нет, то возвращает значение по умолчанию, представленное пользователем
        }

        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num : arr2) {
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int key : map1.keySet()) { //map.keySet() метод, который возвращает набор всех ключей
            if (map2.containsKey(key)) {
                int minFreq = Math.min(map1.get(key), map2.get(key));
                for (int i = 0; i < minFreq; i++) {
                    result.add(key);
                }
            }
        }
        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}

/*
6. Даны два массива x[1] . . . x[k] и y[1] . . . y[l].
Найти их «пересечение», то есть массив z[1] . . . z[m] ,
содержащий их общие элементы,
причем кратность каждого элемента в массиве z
равняется минимуму из его кратностей в массивах x и y.
Число действий порядка O(k + l) (два прохода по массивам и линейный проход по ключам).
* */