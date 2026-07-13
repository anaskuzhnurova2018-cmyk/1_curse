package ModuleTests.hw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;

public class JsonFile {
    public int checkJsonFile(String file) throws IOException {
        if (file == null) throw new NullPointerException();
        Stack<Character> stack = new Stack<>();
        if (file.endsWith(".json")) {
            String content = Files.readString(Path.of(file));
            for (int i = 0; i < content.length(); i++) {
                char c = content.charAt(i);
                if (c == '{' || c == '[') {
                    stack.push(c); //положили в стэк
                } else if (c == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return i + 1;
                    }
                } else if (c == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return i + 1;
                    }
                }
            }
            if (!stack.isEmpty()) {
                return content.length();
            }
            return 0;
        }
        throw new NottheFileWeNeed_text("Неверный формат файла - нужен текстовый");
    }
}

/*
3. Написать метод, считывающий файл JSON (имя файла передается через аргумент),
 и определяющий корректность расставленных скобок {{}}, [] возвращает 0,
 если все правильно, или позицию проблемы (начиная с 1)
Метод должен бросать исключения
NullPointer если передали пустое имя файла
Исключение, если файл не найден,
Исключение, если файл не текстовый (проверка на буквы, цифры, знаки препинания)

метод 3 задания протестировать на все исключения и на корректность работы
* */