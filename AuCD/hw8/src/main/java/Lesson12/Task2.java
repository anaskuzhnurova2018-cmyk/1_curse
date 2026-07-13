package Lesson12;

/*
метод Карацибы:

A = a1 * 2^m + a0
B = b1 * 2^m + b0

a1,b1 - старшие половины
a0, b0 - младшие половины
m = n / 2 (середина разрядности)
в обычном умножении нужно вычислить 4 произведения, а в методе Карацибы - 3
p1 = a1*b1
p2 = a0*b0
p3 = (a1+a0)*(b1+b0)
a1b0 + a0b1 = p3 - p2 - p1
итог:
A * B = p1 * 2^m + (p3 - p2- p1) * 2^m + p2
* */

public class Task2 {
    public static void main(String[] args) {

        int A = 0b10011011;
        int B = 0b10111010;

        System.out.println(karatsuba(A, B));
    }


    public static int karatsuba(int A, int B) {
        if (A == 0 || B == 0) return 0;

        int n = 0;

        int A2 = A;
        while (A2 > 0) {
            A2 = A2 >> 1;
            n++;
        } //считаем кол-во бит в числе

        if (n == 1) return A * B;

        int a1 = A >> (n/2); //берем старшую половину (сдвиг вправо)
        int m = n/ 2;
        int mask = (1 << m) - 1; //например, для m = 4: 0b1111
        int a0 = A & mask;

        int b1 = B >> (n/2);
        int b0 = B & mask;

        int p1 = karatsuba(a1, b1);
        int p2 = karatsuba(a0, b0);
        int p3 = karatsuba((a1 + a0), (b1 + b0));

        //степень двойки
        int x = 1 << m; //это  2^m
        int x2 = x << m; //2^(2m)

        return p1 * x2 + (p3 - p2 -p1) * x + p2;
    }
}

/*
2. Найдите произведение чисел 10011011 и 10111010
(в двоичной системе счисления),
используя алгоритм умножения чисел,
основанный на методе разделяй и властвуй.
* */