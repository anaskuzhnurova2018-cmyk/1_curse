import java.util.Stack;

public class Task5_8 {
    public static void main(String[] args) {
        String s = "(())()";
        System.out.println(isCorrect(s));
    }

    public static boolean isCorrect(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i); //получаем текущий символ
            if (current == '(') {
                stack.push(current); //положили открывающую скобку в стек
            } else if (current == ')') {
                if (stack.isEmpty()) { //нет пары для этой скобки
                    return false;
                }
                stack.pop(); //если нашли пару, то удаляем ее
            }
        }
        return stack.isEmpty();
    }
}

/*
8. Написать программу, которая определяет,
является ли введенная скобочная структура правильной.
Примеры правильных скобочных выражений: (), (())(), ()(), ((())),
неправильных — )(, ())((), (, )))), ((()).(Сложность O(n))
* */