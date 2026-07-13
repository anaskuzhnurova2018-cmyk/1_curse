import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

//как работает алгоритм?
/*
fft - значения в точках (корни из единицы)
поэлементное умножение
ifft - коэффициенты результата
* */

public class task12_7 {
    public static List<Complex> fft(List<Complex> P) { //прямое преобразование Фурье
        int n = P.size();
        if (n == 1) {
            return new ArrayList<>(P); //базовый случай рекурсии
        }
        // поворот в круге и множитель с таким поворотом
        //работаем с корнями n-й степени из единицы, они лежат на единичной окружности в комплексной плоскости
        double angle = 2 * Math.PI / n; //угол между соседними корнями
        Complex a = new Complex(Math.cos(angle), Math.sin(angle)); //главный примитивный корень, он нужен для поворота значений при объединении результатов

        List<Complex> Pe = new ArrayList<>(); //четные индексы
        List<Complex> Po = new ArrayList<>(); //нечетные

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0)
                Pe.add(P.get(i));
            else
                Po.add(P.get(i));
        }

        List<Complex> ye = fft(Pe); //рекурсивный вызов для четных
        List<Complex> yo = fft(Po); //рекурсивный вызов для нечетных

        List<Complex> y = new ArrayList<>(); // до половины будут одни, а после их симметричные братишки
        for (int i = 0; i < n; i++) {
            y.add(new Complex(0, 0)); //инициализируем результирующий массив нулями
        }

        Complex aPow = new Complex(1, 0); //текущая степень корня
        for (int j = 0; j < n / 2; j++) {
            Complex temp = aPow.mul(yo.get(j));
            // y[j] = ye[j] + a^j · yo[j] //верхняя половина
            //формула объединения
            y.set(j, ye.get(j).add(temp));
            // СИММЕТРИЯЯЯЯЯЯЯЯЯЯЯЯЯЯ (свойства корней из единицы), то есть вычисляем сразу два значения результата за одну итерацию цикла
            // y[j + n/2] = ye[j] - a^j · yo[j] - нижняя половина
            y.set(j + n / 2, ye.get(j).sub(temp));

            // Переходим к следующей степени корня: a^(j+1) = a^j * a
            aPow = aPow.mul(a);
        }

        return y;
    }

    public static List<Complex> ifft(List<Complex> P) { //обратное преоьразование фурье
        int n = P.size();
        List<Complex> sopr = new ArrayList<>();
        for (Complex c : P) {
            //сопряженные числа: меняем знак у мнимой части (a+bi -> a-bi): обратное БПФ можно вычислить через прямое БПФ, если взять сопряженные числа
            sopr.add(new Complex(c.r, -c.i));
        }
        List<Complex> result = fft(sopr); //применяем обычное fft к сопряженным

        List<Complex> finalResult = new ArrayList<>();
        for (Complex c : result) {
            //делим на n и снова берем сопряженное , чтобы получить правильный рез-тат + менеяем знак мнимой части обратно
            finalResult.add(new Complex(c.r / n, -c.i / n));
        }
        return finalResult;
    }

    public static int[] mulPols(int[] a, int[] b) { //умножение многочленов

        int resultSize = a.length + b.length - 1; //размер рез-тат умножения двух многочленов
        int n = 1;
        while (n < resultSize) {
            n *= 2; //находим ближающую степень двойки: БПФ работает только тогда, когда размер массива - степень двойки
        }

        List<Complex> A = new ArrayList<>();
        List<Complex> B = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // заполняем массивы комплексными числами; коэф-ты многочлена - это действительная часть
             //остальное заполняем нулям до размера n
            A.add(i < a.length ? new Complex(a[i], 0) : new Complex(0, 0));
            B.add(i < b.length ? new Complex(b[i], 0) : new Complex(0, 0));
        }

        List<Complex> FA = fft(A); //переходим в частотную область (значения в точках)
        List<Complex> FB = fft(B);

        List<Complex> FC = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //в частотной области умножение многочленов превращается в поэлеметное умножение значений
            FC.add(FA.get(i).mul(FB.get(i)));
        }

        List<Complex> C = ifft(FC); //возвращаемся обратно в область коэф-тов

        int[] result = new int[resultSize];
        for (int i = 0; i < resultSize; i++) {
            //округляем до ближайшего целого и берем только действительную часть, так как мнимая должна быть примерна = 0
            result[i] = (int) Math.round(C.get(i).r);
        }

        return result;
    }


    public static void main(String[] args) {
        // A(x) = 1 + 2x + 3x2
        // B(x) = 4 + 5x
        int[] a = {1, 2, 3};
        int[] b = {4, 5};

        int[] res = mulPols(a, b);
        System.out.println("Коэффициенты: " + Arrays.toString(res));
        // [4, 13, 22, 15]
    }
}
