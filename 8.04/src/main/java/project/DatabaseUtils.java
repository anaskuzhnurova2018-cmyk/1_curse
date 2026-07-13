package project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatabaseUtils {

    private static final String FILE_NAME = "db.tbl";
    private static long fileSize;
    private static Map<Integer, Long> indexes; //id и позиция в файле

    public DatabaseUtils() throws  IOException{
        init();
    }

    public void save(Wastes wastes) throws IOException{
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); //для сериализации объекта в байты
             FileOutputStream fos = new FileOutputStream(FILE_NAME, true); //для записи в файл
             ObjectOutputStream os = new ObjectOutputStream(bos); //сериализует объект
             DataOutputStream dos = new DataOutputStream(fos)) { //записывает примитивы
            os.writeObject(wastes); //сериализуем объект в байты
            byte[] buff = bos.toByteArray();
            dos.writeInt(wastes.getId());
            dos.writeByte(1);
            dos.writeInt(buff.length);
            dos.write(buff);
            fos.flush();

            indexes.put(wastes.getId(), fileSize);
            fileSize += 4 + 1 + 4 + buff.length;
        }
    }

    public void init() throws IOException{
        indexes = new HashMap<>();
        fileSize = 0;
        long position = 0L;
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) {
                int id = dis.readInt();
                byte flag = dis.readByte();
                int size = dis.readInt();
                dis.skip(size);
                if (flag == 1) {
                    indexes.put(id, position);
                }
                position += 9 + size;
                fileSize = position;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Зафиксировали первый старт");
        }
    }

    public Wastes findByteId(Integer id) throws IOException, ClassNotFoundException {
        Wastes result;
        Long position = indexes.get(id);
        if (position == null) {
            throw new NoSuchElementException();
        }
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             DataInputStream dis = new DataInputStream(fis)) {
            //здесь должен быть цикл
            dis.skip(position);
            dis.readInt(); //пропускаем id
            dis.read(); //пропускаем flag
            int size = dis.readInt();
            byte[] buff = new byte[size];
            dis.readFully(buff);
            //по сюда
            ByteArrayInputStream bis = new ByteArrayInputStream(buff);
            ObjectInputStream ois = new ObjectInputStream(bis); //десериализуем байты обратно в объект
            result = (Wastes) ois.readObject();
        }
        return result;
    }

    //дз
    public void updateFlag(int id, byte newFlag) throws IOException {
        Long position = indexes.get(id);
        if (position == null) throw new NullPointerException();
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) { //открываем файл для записи в середину, fileOutputStream не можем использовать, потому что он перезаписывает нужную позицию, но уничтожает все, что идет после нее
            file.seek(position); //смещение указателя на нужную позицию
            file.skipBytes(4); //пропускаем id (занимает 4 байта, так как int)
            file.writeByte(newFlag);
        }
    }

    //дз
    public void deleteById(int id) throws IOException {
        updateFlag(id, (byte) 0);
    }

    //дз
    public void exportToHtml(String fileName) throws IOException, ClassNotFoundException {
        List<Wastes> activeRecords = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(DatabaseUtils.FILE_NAME);
        DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) { //available() проверяет остались ли в файле байты, которые можно прочитать (хотя бы 1)
                int id = dis.readInt();
                byte flag = dis.readByte();
                int size = dis.readInt();

                if (flag == 1) {
                    byte[] buff = new byte[size];
                    dis.readFully(buff); //прочитали байты объекта

                    //десериализуем объект (процесс восстановления объекта из последовательности байт)
                    ByteArrayInputStream bais = new ByteArrayInputStream(buff);
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Wastes record = (Wastes) ois.readObject();
                    activeRecords.add(record);
                } else {
                    dis.skip(size); //пропускаем удаленные
                }
            }
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>\n")
                    .append("<html>\n")
                    .append("<head>\n")
                    .append("    <meta charset=\"utf-8\"/>\n")
                    .append("</head>\n")
                    .append("<body>\n")
                    .append("<h1>Доходы/Расходы</h1>\n")
                    .append("<table>\n")
                    .append("    <tr>\n")
                    .append("        <th>id</th><th>Тип</th><th>Название</th><th>Сумма</th><th>Дата</th><th>Примечание</th>\n")
                    .append("    </tr>\n");
            for (Wastes record : activeRecords) {
                String comment = record.getComment() != null ? record.getComment() : "-";
                String type =record.getType() == 1 ? "Доход" : "Расход";
                String date = new SimpleDateFormat("dd.MM.yyyy").format(record.getDate());
                html.append("    <tr>\n")
                        .append("        <td>").append(record.getId()).append("</td>")
                        .append("<td>").append(type).append("</td>")
                        .append("<td>").append(record.getName()).append("</td>")
                        .append("<td>").append(record.getSum()).append("</td>")
                        .append("<td>").append(date).append("</td>")
                        .append("<td>").append(comment).append("</td>")
                        .append("\n    </tr>\n");
            }

            html.append("</table>\n")
                    .append("</body>\n")
                    .append("</html>");

            Path path = Path.of(fileName);
            Files.writeString(Path.of(fileName), html.toString());
            System.out.println("Файл создан: " + path.toAbsolutePath());
        }
    }
}
