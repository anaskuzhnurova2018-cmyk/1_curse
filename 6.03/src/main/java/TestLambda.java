import java.util.function.Predicate;

public class TestLambda {
    public static void main(String[] args) {
        Predicate<String> predicate =
                (s) -> s.startsWith("Муз");
    }
}

/*
(s)
Параметр лямбды — переменная s типа String
→ Это та строка, которую мы будем проверять

->
Оператор "стрелка", означает: "принимаем то, что слева, делаем то, что справа"

s.startsWith("Муз")
Тело лямбды — вызывается метод строки
→ Возвращает true, если строка начинается с "Муз"
* */
