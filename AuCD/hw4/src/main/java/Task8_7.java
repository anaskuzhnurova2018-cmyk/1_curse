public class Task8_7 {
    public static void main(String[] args) {
        String s = "the sky is blue";
        char[] array = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            array[i] = s.charAt(i);
        }

        reverse(array, 0, array.length-1); //разворачиваем всю строку

        int start = 0;
        for (int i = 0; i <= array.length; i++) { //каждое слово разворачиваем
            if (i == array.length || array[i] == ' ') { //дошли до конца массива или наткнулись на пробел
                reverse(array, start, i - 1);
                start = i+1; //сдвигаем начало на первый символ следующего слова, пропуская пробел
            }
        }

        System.out.println(array);
    }

    public static void reverse(char[] array, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }
}

/*
Есть строка.
Нужно развернуть строку так,
чтобы каждое из слов осталось в естественном порядке.
Например. Input 1: A = "the sky is blue"
Output 1: "blue is sky the"
Input 2: A = "this is ib"   
Output 2: "ib is this".
Сложность линейная, с О(1) дополнительной памяти.
* */