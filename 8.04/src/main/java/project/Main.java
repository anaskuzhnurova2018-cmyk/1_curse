package project;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        DatabaseUtils db = new DatabaseUtils();
        Scanner scanner = new Scanner(System.in);
         while (true) {
             System.out.println("\nМеню");
             System.out.println("1) Добавить запись");
             System.out.println("2) Найти запись по ID");
             System.out.println("3) Удалить запись по ID");
             System.out.println("4) Вывод всех данных");
             System.out.println("5) Выход из программы");
             System.out.println("Выберите действие :");

             int choice = scanner.nextInt();
             scanner.nextLine();
             switch (choice) {
                 case 1:
                     System.out.println("Введите ID предмета: ");
                     int id = scanner.nextInt();
                     scanner.nextLine();
                     System.out.println("Если это доход, введите 1, если расход, то -1");
                     int type = scanner.nextInt();
                     scanner.nextLine(); //очищаем буфер (остается символ переноса строки)
                     System.out.println("Введите название предмета: ");
                     String name = scanner.nextLine();
                     System.out.println("Введите дату покупки в формате dd.MM.yyyy: ");
                     String dateStr = scanner.nextLine();
                     SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                     Date date = sdf.parse(dateStr);
                     System.out.println("Напишите комментарий: ");
                     String comment = scanner.nextLine();
                     System.out.println("Введите сумму покупки: ");
                     float sum = scanner.nextFloat();
                     scanner.nextLine();
                     Wastes waste = new Wastes(id, type, name, date, comment, sum);
                     db.save(waste);
                     break;
                 case 2:
                     System.out.println("Введите ID предмета: ");
                     int ID = scanner.nextInt();
                     System.out.println(db.findByteId(ID));
                     break;
                 case 3:
                     System.out.println("Введите ID предмета: ");
                     int id1 = scanner.nextInt();
                     db.deleteById(id1);
                     break;
                 case 4:
                     db.exportToHtml("students.html");
                     break;
                 case 5:
                     System.out.println("Выход из программы");
                     return;
                 default:
                     System.out.println("Неверный выбор. Попробуйте снова!");
             }
         }

    }
}

